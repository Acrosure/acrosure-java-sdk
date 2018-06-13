package com.acrosure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ApplicationResource {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;

    ApplicationResource(HttpClient httpClient) {
        this.httpClient = httpClient;
        METHOD_GROUP = "applications";
    }

    public Application get(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("application_id", applicationId);
        responseData = (JSONObject) httpClient.call("get", METHOD_GROUP, requestPayload);

        return new Application(
                (String) responseData.get("product_id"),
                (String) responseData.get("id"),
                (JSONObject) responseData.get("form_data"),
                ApplicationStatus.valueOf((String) responseData.get("status")));
    }

    public ArrayList<InsurancePackage> getPackages(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<InsurancePackage> insurancePackages = new ArrayList<>();

        requestPayload.put("application_id", applicationId);
        responseData = (JSONArray) httpClient.call("get-packages", METHOD_GROUP, requestPayload);

        for (Object object: responseData) {
            JSONObject jsonObject = (JSONObject) object;
            insurancePackages.add(new InsurancePackage(
                    (String) jsonObject.get("insurer_package_code"),
                    (String) jsonObject.get("name"),
                    (Double) jsonObject.get("amount"),
                    (Long) jsonObject.get("amount_with_tax"),
                    jsonObject));
        }

        return insurancePackages;
    }


    public Application create(String productId, JSONObject data) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put("product_id", productId);
        requestPayload.put("form_data", data);

        responseData = (JSONObject) httpClient.call("create", METHOD_GROUP, requestPayload);

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

        responseData = (JSONObject) httpClient.call("update", METHOD_GROUP, requestPayload);
        application.setStatus(ApplicationStatus.valueOf((String) responseData.get("status")));

        return application;
    }

    public ArrayList<Policy> confirm(Application application) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<Policy> policies = new ArrayList<>();

        requestPayload.put("application_id", application.getId());
        responseData = (JSONArray) httpClient.call("confirm", METHOD_GROUP, requestPayload);
        application.setStatus(ApplicationStatus.COMPLETED);

        for (Object object: responseData)
            policies.add(Policy.parseJson((JSONObject) object, application));

        return policies;
    }
}