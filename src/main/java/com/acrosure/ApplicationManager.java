package com.acrosure;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ApplicationManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;

    ApplicationManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        METHOD_GROUP = "applications";
    }

    public Application get(String applicationId) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), applicationId);
        responseData = (JSONObject) httpClient.call(METHOD_GROUP, Methods.GET.toString(), requestPayload);

        return Application.parseJson(responseData);
    }

    public ArrayList<InsurancePackage> getPackages(String applicationId) throws IOException, AcrosureException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<InsurancePackage> insurancePackages = new ArrayList<>();

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), applicationId);
        responseData = (JSONArray) httpClient.call(METHOD_GROUP, Methods.GET_PACKAGES.toString(), requestPayload);

        for (Object object: responseData)
            insurancePackages.add(InsurancePackage.parseJson((JSONObject) object));

        return insurancePackages;
    }


    public Application create(String productId, JSONObject data) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject(), responseData;

        requestPayload.put(Application.Fields.PRODUCT_ID.toString(), productId);
        requestPayload.put(Application.Fields.FORM_DATA.toString(), data);

        responseData = (JSONObject) httpClient.call(METHOD_GROUP, Methods.CREATE.toString(), requestPayload);

        return Application.parseJson(responseData);
    }

    public Application update(Application application) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject(), responseData;
        Application applicationTemp;

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), application.id());
        requestPayload.put(Application.Fields.FORM_DATA.toString(), application.data());
        requestPayload.put(Application.Fields.INSURER_PACKAGE_CODE.toString(), application.insurerPackageCode());
        requestPayload.put(Application.Fields.INSURER_PACKAGE_NAME.toString(), application.insurerPackageName());
        requestPayload.put(Application.Fields.AMOUNT.toString(), application.amount());
        requestPayload.put(Application.Fields.AMOUNT_WITH_TAX.toString(), application.amountWithTax());
        requestPayload.put(Application.Fields.REF1.toString(), application.reference(1));
        requestPayload.put(Application.Fields.REF2.toString(), application.reference(2));
        requestPayload.put(Application.Fields.REF3.toString(), application.reference(3));

        responseData = (JSONObject) httpClient.call(METHOD_GROUP, Methods.UPDATE.toString(), requestPayload);
        applicationTemp = Application.parseJson(responseData);

        application.setStatus(applicationTemp.status());
        application.setUpdatedAt(applicationTemp.updatedAt());
        application.setErrorFields(applicationTemp.errorFields());
        application.setErrorMessage(applicationTemp.errorMessage());

        return application;
    }

    public ArrayList<Policy> confirm(Application application) throws IOException, AcrosureException, ParseException {
        JSONObject requestPayload = new JSONObject();
        JSONArray responseData;
        ArrayList<Policy> policies = new ArrayList<>();
        Application applicationTemp;

        requestPayload.put(Application.Fields.APPLICATION_ID.toString(), application.id());
        responseData = (JSONArray) httpClient.call(METHOD_GROUP, Methods.CONFIRM.toString(), requestPayload);
        application.setStatus(ApplicationStatus.COMPLETED);

        for (Object object: responseData)
            policies.add(Policy.parseJson((JSONObject) object, application));

        applicationTemp = get(application.id());

        application.setStatus(applicationTemp.status());
        application.setInsurerApplicationNo(applicationTemp.insurerApplicationNo());
        application.setUpdatedAt(applicationTemp.updatedAt());
        application.setPolicyIds(applicationTemp.policyIds());

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