package com.acrosure;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.text.ParseException;

class ApplicationManagerTest {

    @Test
    void get_goodNetworkWithValidApplicationId_returnsJSONAware() {
        IHttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_OK);
        ApplicationManager manager = new ApplicationManager(httpClient);
        Application application = null;

        try {
            application = manager.get("sandbox_appl_Cga9GqjAAoC8aLwp");
        } catch (ParseException | IOException | AcrosureException e) {
            e.printStackTrace();
        }

        assertThat(application.getId(), containsString("sandbox_appl_Cga9GqjAAoC8aLwp"));
    }

    @Test
    void get_goodNetworkWithInvalidApplicationId_throwsAcrosureException() {
        IHttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_OK);
        Throwable exception = assertThrows(AcrosureException.class, () -> {
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("invalid_application_id");
        });

        assertThat(exception.getMessage(), containsString("record not found"));
    }

    @Test
    void get_poorNetworkWithValidApplicationId_throwsIOException() {
        Throwable exception = assertThrows(IOException.class, () -> {
            IHttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_ERROR);
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("sandbox_appl_Cga9GqjAAoC8aLwp");
        });

        assertThat(exception.getMessage(), containsString("Network error"));
    }

    @Test
    void get_goodNetworkWithReturnedJsonMalformed_throwsParseException() {
        Throwable exception = assertThrows(AcrosureException.class, () -> {
            IHttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_OK);
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("malformed json");
        });

        assertThat(exception.getMessage(), containsString("Malformed responded JSON"));
    }

    @Test
    void get_goodNetworkWithWrongDateFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> {
            IHttpClient httpClient = new FakeHttpClient(Scenario.NETWORK_OK);
            ApplicationManager manager = new ApplicationManager(httpClient);
            manager.get("wrong date format");
        });
    }

//    @Test
//    void getPackages() {
//    }
//
//    @Test
//    void create() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void confirm() {
//    }

    class FakeHttpClient implements IHttpClient {
        private Scenario scenario;

        FakeHttpClient(Scenario scenario) {
            this.scenario = scenario;
        }

        @Override
        public JSONAware call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException {

            if (scenario == Scenario.NETWORK_OK) {
                switch (method) {
                    case "get":
                        if ((param.get("application_id")).equals("sandbox_appl_Cga9GqjAAoC8aLwp")) {
                            String json = "{\"id\":\"sandbox_appl_Cga9GqjAAoC8aLwp\",\"form_data\":{\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"id_card\":\"1349900696510\",\"card_type\":\"I\",\"countries\":[\"UNITED ARAB EMIRATES\"],\"expiry_date\":\"2018-06-30T07:16:02.461Z\",\"policy_date\":\"2018-06-20T07:16:00.071Z\",\"policy_unit\":\"D\",\"company_name\":\"SRIKOTE \",\"insurer_list\":[{\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"title\":\"MR.\",\"address\":{\"moo\":\"11\",\"lane\":\"\",\"street\":\"KLANG AWUT\",\"village\":\"\",\"district\":\"Mueangubonratchathani\",\"province\":\"Ubon Ratchathani\",\"address_no\":\"315\",\"postal_code\":\"34000\",\"subdistrict\":\"Khamyai\"},\"id_card\":\"1349900696510\",\"nominee\":null,\"birthdate\":\"1995-04-05T07:18:44.543Z\",\"card_type\":\"I\",\"last_name\":\"NAEWCHAMPA\",\"first_name\":\"SRIKOTE \"}],\"customer_title\":\"MR.\",\"customer_last_name\":\"NAEWCHAMPA\",\"customer_first_name\":\"SRIKOTE \"},\"status\":\"COMPLETED\",\"amount\":911.89,\"amount_with_tax\":980,\"source\":\"PARTNER\",\"ref1\":\"\",\"ref2\":\"\",\"ref3\":\"\",\"insurer_package_code\":\"ITA0402\",\"insurer_package_name\":\"\",\"insurer_application_no\":\"Q4003947180009644\",\"package_data\":null,\"language\":\"EN\",\"created_at\":\"2018-06-14T10:53:12.322259Z\",\"updated_at\":\"2018-06-21T00:08:03.470024Z\",\"product_id\":\"prod_ta\",\"user_id\":\"user_sample_partner\",\"team_id\":\"team_sample\",\"product_code\":\"\",\"error_fields\":null,\"error_message\":\"\",\"policy_ids\":[\"sandbox_plcy_XmHUZzFUNOHaYfqY\"]}";
                            return (JSONAware) JSONValue.parse(json);
                        } else if ((param.get("application_id")).equals("malformed json")) {
                            JSONObject returnedObject = new JSONObject();
                            returnedObject.put("key", "value");
                            return returnedObject;
                        } else if ((param.get("application_id")).equals("wrong date format")) {
                            String json = "{\"id\":\"sandbox_appl_Cga9GqjAAoC8aLwp\",\"form_data\":{\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"id_card\":\"1349900696510\",\"card_type\":\"I\",\"countries\":[\"UNITED ARAB EMIRATES\"],\"expiry_date\":\"2018-06-30T07:16:02.461Z\",\"policy_date\":\"2018-06-20T07:16:00.071Z\",\"policy_unit\":\"D\",\"company_name\":\"SRIKOTE \",\"insurer_list\":[{\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"title\":\"MR.\",\"address\":{\"moo\":\"11\",\"lane\":\"\",\"street\":\"KLANG AWUT\",\"village\":\"\",\"district\":\"Mueangubonratchathani\",\"province\":\"Ubon Ratchathani\",\"address_no\":\"315\",\"postal_code\":\"34000\",\"subdistrict\":\"Khamyai\"},\"id_card\":\"1349900696510\",\"nominee\":null,\"birthdate\":\"1995-04-05T07:18:44.543Z\",\"card_type\":\"I\",\"last_name\":\"NAEWCHAMPA\",\"first_name\":\"SRIKOTE \"}],\"customer_title\":\"MR.\",\"customer_last_name\":\"NAEWCHAMPA\",\"customer_first_name\":\"SRIKOTE \"},\"status\":\"COMPLETED\",\"amount\":911.89,\"amount_with_tax\":980,\"source\":\"PARTNER\",\"ref1\":\"\",\"ref2\":\"\",\"ref3\":\"\",\"insurer_package_code\":\"ITA0402\",\"insurer_package_name\":\"\",\"insurer_application_no\":\"Q4003947180009644\",\"package_data\":null,\"language\":\"EN\",\"created_at\":\"2018-06-14T10:53:12.322259Z\",\"updated_at\":\"2018-06-21TZ\",\"product_id\":\"prod_ta\",\"user_id\":\"user_sample_partner\",\"team_id\":\"team_sample\",\"product_code\":\"\",\"error_fields\":null,\"error_message\":\"\",\"policy_ids\":[\"sandbox_plcy_XmHUZzFUNOHaYfqY\"]}";
                            return (JSONAware) JSONValue.parse(json);
                        } else {
                            throw new AcrosureException("record not found", 400);
                        }
                    case "get-packages":
                    case "create":
                    case "update":
                    case "confirm":
                }
            } else {
                throw new IOException("Network error");
            }

            return null;
        }
    }

    enum Scenario {
        NETWORK_OK, NETWORK_ERROR;
    }
}