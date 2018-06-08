package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ApplicationResource {
    private final HttpClient httpClient;
    private final String RESOURCE;

    ApplicationResource(HttpClient httpClient) {
        this.httpClient = httpClient;
        RESOURCE = "applications";
    }

    public Application get(String applicationId) throws IOException {
        ApiResponse apiResponse;
        JSONObject requestPayload = new JSONObject();

        requestPayload.put("application_id", applicationId);
        apiResponse = httpClient.call("get", RESOURCE, requestPayload);

        if (apiResponse.getHttpStatus() != 200)
            throw new RuntimeException(
                    "Status: " + apiResponse.getHttpStatus() + "," + apiResponse.getContent().get("message"));

        return new Application((JSONObject) apiResponse.getContent().get("data"));
    }

    public JSONObject getPackages(String applicationId) throws IOException {
        ApiResponse apiResponse;
        JSONObject requestPayload = new JSONObject();

        requestPayload.put("application_id", applicationId);
        apiResponse = httpClient.call("get-packages", RESOURCE, requestPayload);

        if (apiResponse.getHttpStatus() != 200)
            throw new RuntimeException(
                    "Status: " + apiResponse.getHttpStatus() + "," + apiResponse.getContent().get("message"));

        return apiResponse.getContent();
    }

    /**
     * @TODO How to handle errors from server? (Not errors from Java itself)
     */
    public Application create(String productId, JSONObject data) throws IOException {
        ApiResponse apiResponse;
        JSONObject requestPayload = new JSONObject();

        requestPayload.put("product_id", productId);
        requestPayload.put("form_data", data);

        apiResponse = httpClient.call("create", RESOURCE, requestPayload);

        if (apiResponse.getHttpStatus() != 200)
            throw new RuntimeException(
                    "Status: " + apiResponse.getHttpStatus() + "," + apiResponse.getContent().get("message"));

        return new Application(
                productId,
                (String) ((JSONObject) apiResponse.getContent().get("data")).get("id"),
                data,
                "INITIAL");
    }

    public Application update(Application application) throws IOException {
        ApiResponse apiResponse;
        JSONObject requestPayload = new JSONObject();

        requestPayload.put("application_id", application.getId());
        requestPayload.put("form_data", application.data());

        apiResponse = httpClient.call("update", RESOURCE, requestPayload);

        if (apiResponse.getHttpStatus() != 200)
            throw new RuntimeException(
                    "Status: " + apiResponse.getHttpStatus() + "," + apiResponse.getContent().get("message"));

        application.setStatus((String) ((JSONObject) apiResponse.getContent().get("data")).get("status"));

        return application;
    }
}