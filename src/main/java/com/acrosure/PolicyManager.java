package com.acrosure;

import com.acrosure.form.PolicyQuery;
import com.acrosure.resource.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PolicyManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    PolicyManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "policies";
        this.mapper = new ObjectMapper();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(df);
    }

    public Policy get(String policyId) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("policy_id", policyId);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Policy.class);
    }

    public PolicyList list(PolicyQuery query) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.valueToTree(query);

        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.LIST.toString(), requestPayload);

        return mapper.treeToValue(responseData, PolicyList.class);
    }

    private enum Methods {
        GET("get"),
        LIST("list");

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