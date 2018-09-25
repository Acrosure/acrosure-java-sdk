package com.acrosure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class ApplicationManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    ApplicationManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "applications";
        this.mapper = new ObjectMapper();

        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
    }

    public Application get(String applicationId) throws IOException, AcrosureException {
        ObjectNode requestPayload = (ObjectNode) mapper.readTree("{}");

        requestPayload.put("application_id", applicationId);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET.toString(), requestPayload);

        return mapper.treeToValue(responseData, Application.class);
    }

    public Package[] getPackages(String applicationId) throws IOException, AcrosureException {
        ObjectNode requestPayload = (ObjectNode) mapper.readTree("{}");

        requestPayload.put("application_id", applicationId);
        ArrayNode responseData = (ArrayNode) httpClient.call(METHOD_GROUP, Methods.GET_PACKAGES.toString(), requestPayload);

        return mapper.treeToValue(responseData, Package[].class);
    }


    public Application create(
            String productId,
            ObjectNode basicData,
            ObjectNode packageOptions,
            ObjectNode additionalData) throws IOException, AcrosureException {
        ObjectNode requestPayload = (ObjectNode) mapper.readTree("{}");

        requestPayload.put("product_id", productId);

        if (basicData != null) {
            requestPayload.putNull("basic_data");
            requestPayload.replace("basic_data", basicData);
        } else if (packageOptions != null) {
            requestPayload.putNull("package_options");
            requestPayload.replace("package_options", packageOptions);
        } else if (additionalData != null) {
            requestPayload.putNull("additional_data");
            requestPayload.replace("additional_data", additionalData);
        }

        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.CREATE.toString(), requestPayload);

        return mapper.treeToValue(responseData, Application.class);
    }

    public Application update(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.valueToTree(application);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.UPDATE.toString(), requestPayload);

        Application origin = mapper.treeToValue(responseData, Application.class);

        return application.copy(origin);
    }

    public Policy[] confirm(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = (ObjectNode) mapper.readTree("{}");

        requestPayload.put("application_id", application.getId());
        ArrayNode responseData = (ArrayNode) httpClient.call(METHOD_GROUP, Methods.CONFIRM.toString(), requestPayload);

        Application origin = get(application.getId());
        application.copy(origin);

        return mapper.treeToValue(responseData, Policy[].class);
    }

    private enum Methods {
        GET("get"),
        GET_PACKAGES("get-packages"),
        CREATE("create"),
        UPDATE("update"),
        CONFIRM("confirm");

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