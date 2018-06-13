package com.acrosure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<InsurancePackage> getPackages(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<InsurancePackage> insurancePackages = new ArrayList<>();

        requestPayload.put("application_id", applicationId);
        responseData = (JSONArray) httpClient.call("get-packages", RESOURCE, requestPayload);

        for (Object object: responseData) {
            insurancePackages.add(new InsurancePackage((JSONObject) object));
        }

        return insurancePackages;
    }


    public Application create(String productId, JSONObject data) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("product_id", productId);
        requestPayload.put("form_data", data);

        responseData = (JSONObject) httpClient.call("create", RESOURCE, requestPayload);

        return new Application(productId, (String) responseData.get("id"), data, ApplicationStatus.INITIAL);
    }

    public Application update(Application application) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;
        InsurancePackage insurancePackage = application.getInsurancePackage();

        requestPayload.put("application_id", application.getId());
        requestPayload.put("form_data", application.data());

        if (insurancePackage != null) {
            requestPayload.put("insurer_package_code", insurancePackage.getInsurerPackageCode());
            requestPayload.put("amount", insurancePackage.getAmount());
            requestPayload.put("amount_with_tax", insurancePackage.getAmountWithTax());
        }

        responseData = (JSONObject) httpClient.call("update", RESOURCE, requestPayload);
        application.setStatus(ApplicationStatus.valueOf((String) responseData.get("status")));

        return application;
    }
}