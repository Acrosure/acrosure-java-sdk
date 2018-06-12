package com.acrosure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class ApplicationResource {
    private final HttpClient httpClient;
    private final String RESOURCE;

    ApplicationResource(HttpClient httpClient) {
        this.httpClient = httpClient;
        RESOURCE = "applications";
    }

    public Application get(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("application_id", applicationId);
        responseData = (JSONObject) httpClient.call("get", RESOURCE, requestPayload);

        return new Application(responseData);
    }

    public JSONArray getPackages(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;

        requestPayload.put("application_id", applicationId);
        responseData = (JSONArray) httpClient.call("get-packages", RESOURCE, requestPayload);

        return responseData;
    }


    public Application create(String productId, JSONObject data) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("product_id", productId);
        requestPayload.put("form_data", data);

        responseData = (JSONObject) httpClient.call("create", RESOURCE, requestPayload);

        return new Application(
                productId,
                (String) responseData.get("id"),
                data,
                "INITIAL");
    }

    public Application update(Application application) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("application_id", application.getId());
        requestPayload.put("form_data", application.data());

        responseData = (JSONObject) httpClient.call("update", RESOURCE, requestPayload);
        application.setStatus((String) responseData.get("status"));

        return application;
    }

    public Application setPackage(String pakageId) {
        return null;
    }
}