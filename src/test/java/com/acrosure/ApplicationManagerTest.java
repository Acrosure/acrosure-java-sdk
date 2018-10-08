package com.acrosure;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

class ApplicationManagerTest {

    @Test
    void Get_GoodNetworkWithExistingApplicationId_ReturnsRightApplication() {
        HttpClient httpClient = new FakeHttpClient(Scenario.HAPPY);
        ApplicationManager manager = new ApplicationManager(httpClient);
        Application application = null;

        try {
            application = manager.get("sandbox_appl_1234");
        } catch (IOException | AcrosureException e) {
            e.printStackTrace();
        }

        assertThat(application.getId(), containsString("_appl_"));
    }

    @Test
    void Get_GoodNetworkWithInvalidApplicationId_ThrowsAcrosureException() {
        HttpClient httpClient = new FakeHttpClient(Scenario.INPUT_ERROR);
        Throwable exception = assertThrows(AcrosureException.class, () -> {
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("invalid_application_id");
        });

        assertThat(exception.getMessage(), containsString("input"));
    }

    @Test
    void Get_PoorNetwork_ThrowsIOException() {
        Throwable exception = assertThrows(IOException.class, () -> {
            HttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_ERROR);
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("sandbox_appl_1234");
        });

        assertThat(exception.getMessage(), containsString("Network error"));
    }

    @Test
    void Get_GoodNetworkWithReturnedJsonMalformed_ThrowsIOException() {
        assertThrows(IOException.class, () -> {
            HttpClient httpClient = new FakeHttpClient(Scenario.JSON_MALFORMED);
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("sandbox_appl_1234");
        });
    }

    @Test
    void Get_GoodNetworkWithUnknownJsonFieldReturned_ReturnsApplication() {

    }

    class FakeHttpClient implements HttpClient {
        private Scenario scenario;
        private final ObjectMapper mapper;

        FakeHttpClient(Scenario scenario) {
            this.scenario = scenario;
            this.mapper = new ObjectMapper();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setDateFormat(df);
        }

        @Override
        public JsonNode call(String methodGroup, String method, ObjectNode param) throws IOException, AcrosureException {
            ObjectNode response;

            switch (this.scenario) {
                case HAPPY:
                    switch (method) {
                        case "get":
                        case "create":
                        case "update":
                        default:
                            response = mapper.createObjectNode();
                            response.put("id", param.get("application_id").asText());
                    }
                    return response;
                case NETWORK_ERROR:
                    throw new IOException("Network error");
                case INPUT_ERROR:
                    throw new AcrosureException("input error", 400);
                case ACROSURE_ERROR:
                    throw new AcrosureException("Server failed on executing the request", 500);
                case JSON_MALFORMED:
                    response = (ObjectNode) mapper.readTree("{\"test\":\"missing ending double quote\" \"test\":\"test\"}");
                    return response;
                case JSON_UNKNOWN_FIELD:
                    response = (ObjectNode) mapper.readTree("{\"id\":\"sandbox_appl_1234\"," +
                            " \"nonexistent\":\"something\"," +
                            " \"Oh_yay\": 1234}");
                    return response;
                default:
                    return null;
            }
        }
    }

    enum Scenario {
        HAPPY,
        NETWORK_ERROR,
        INPUT_ERROR,
        ACROSURE_ERROR,
        JSON_MALFORMED,
        JSON_UNKNOWN_FIELD
    }
}