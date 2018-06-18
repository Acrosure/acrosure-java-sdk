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

    public Application get(String applicationId) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), applicationId);
        responseData = (JSONObject) httpClient.call(Methods.GET.toString(), METHOD_GROUP, requestPayload);

        return Application.parseJson(responseData);
    }

    public ArrayList<InsurancePackage> getPackages(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<InsurancePackage> insurancePackages = new ArrayList<>();

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), applicationId);
        responseData = (JSONArray) httpClient.call(Methods.GET_PACKAGES.toString(), METHOD_GROUP, requestPayload);

        for (Object object: responseData) {
            JSONObject jsonObject = (JSONObject) object;
            insurancePackages.add(new InsurancePackage(
                    (String) jsonObject.get(InsurancePackage.Fields.INSURER_PACKAGE_CODE.toString()),
                    (String) jsonObject.get(InsurancePackage.Fields.NAME.toString()),
                    (Double) jsonObject.get(InsurancePackage.Fields.AMOUNT.toString()),
                    (Long) jsonObject.get(InsurancePackage.Fields.AMOUNT_WITH_TAX.toString()),
                    jsonObject));
        }

        return insurancePackages;
    }


    public Application create(String productId, JSONObject data) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put(Application.Fields.PRODUCT_ID.toString(), productId);
        requestPayload.put(Application.Fields.FORM_DATA.toString(), data);

        responseData = (JSONObject) httpClient.call(Methods.CREATE.toString(), METHOD_GROUP, requestPayload);

        return Application.parseJson(responseData);
    }

    public Application update(Application application) throws IOException, AcrosureException, ParseException {
        Application returnedApplication;
        JSONObject requestPayload = new JSONObject(), responseData;
        InsurancePackage insurancePackage = application.getPackageData();

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), application.getId());
        requestPayload.put(Application.Fields.FORM_DATA.toString(), application.data());

        if (insurancePackage != null) {
            requestPayload.put(Application.Fields.INSURER_PACKAGE_CODE.toString(),
                    insurancePackage.getInsurerPackageCode());
            requestPayload.put(Application.Fields.INSURER_PACKAGE_NAME.toString(),insurancePackage.getName());
            requestPayload.put(Application.Fields.INSURER_APPLICATION_NO.toString(),
                    application.getInsurerApplicationNo());
            requestPayload.put(Application.Fields.AMOUNT.toString(), insurancePackage.getAmount());
            requestPayload.put(Application.Fields.AMOUNT_WITH_TAX.toString(), insurancePackage.getAmountWithTax());
        }

        responseData = (JSONObject) httpClient.call(Methods.UPDATE.toString(), METHOD_GROUP, requestPayload);
        returnedApplication = Application.parseJson(responseData);
        application.setStatus(returnedApplication.getStatus());
        application.setUpdatedAt(returnedApplication.getUpdatedAt());

        return application;
    }

    public ArrayList<Policy> confirm(Application application) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<Policy> policies = new ArrayList<>();

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), application.getId());
        responseData = (JSONArray) httpClient.call(Methods.CONFIRM.toString(), METHOD_GROUP, requestPayload);
        application.setStatus(ApplicationStatus.COMPLETED);

        for (Object object: responseData)
            policies.add(Policy.parseJson((JSONObject) object, application));

        application.setPolicies(policies);

        return policies;
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