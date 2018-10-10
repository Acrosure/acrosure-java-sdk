package com.acrosure;

import com.acrosure.resource.Application;
import com.acrosure.resource.PaymentFormData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class Payment2C2PManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    public Payment2C2PManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "payments/2c2p";
        this.mapper = new ObjectMapper();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(df);
    }

    public PaymentFormData getHash(Application application, URL frontEndUrl) throws IOException, AcrosureException {
        return getHash(application.getId(), frontEndUrl.toString());
    }

    public PaymentFormData getHash(String applicationId, URL frontEndUrl) throws IOException, AcrosureException {
        return getHash(applicationId, frontEndUrl.toString());
    }

    public PaymentFormData getHash(Application application, String frontEndUrl) throws IOException, AcrosureException {
        return getHash(application.getId(), frontEndUrl);
    }

    public PaymentFormData getHash(String applicationId, String frontEndUrl) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", applicationId);
        requestPayload.put("frontend_url", frontEndUrl);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET_HASH.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), PaymentFormData.class);
    }

    private enum Methods {
        GET_HASH("get-hash");

        private final String method;

        Methods(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return method;
        }
    }
}
