package com.acrosure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Application {
    private final String id;
    private final JSONObject data;
    private ApplicationStatus status;
    private double amount;
    private double amountWithTax;
    private ApplicationSource source;
    private String[] references;
    private String insurerPackageCode;
    private String insurerPackageName;
    private String insurerApplicationNo;
    private String language;
    private Date createdAt;
    private Date updatedAt;
    private final String productId;
    private String userId;
    private String teamId;
    private ArrayList<ErrorField> errorFields;
    private String errorMessage;

    private ArrayList<String> policyIds;

    Application(
            String id,
            JSONObject formData,
            ApplicationStatus status,
            double amount,
            double amountWithTax,
            ApplicationSource source,
            String[] references,
            String insurerPackageCode,
            String insurerPackageName,
            String insurerApplicationNo,
            String language,
            Date createdAt,
            Date updatedAt,
            String productId,
            String userId,
            String teamId,
            ArrayList<ErrorField> errorFields,
            String errorMessage,
            ArrayList<String> policyIds) {
        this.id = id;
        this.data = formData;
        this.status = status;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
        this.source = source;
        this.references = references;
        this.insurerPackageCode = insurerPackageCode;
        this.insurerPackageName = insurerPackageName;
        this.insurerApplicationNo = insurerApplicationNo;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productId = productId;
        this.userId = userId;
        this.teamId = teamId;
        this.errorFields = errorFields;
        this.errorMessage = errorMessage;
        this.policyIds = policyIds;
    }

    void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    void setErrorFields(ArrayList<ErrorField> errorFields) {
        this.errorFields = errorFields;
    }

    void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    void setInsurerApplicationNo(String insurerApplicationNo) {
        this.insurerApplicationNo = insurerApplicationNo;
    }

    void setPolicyIds(ArrayList<String> policyIds) {
        this.policyIds = policyIds;
    }

    static Application parseJson(JSONObject jsonObject) throws ParseException, AcrosureException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");

        // aggregated objects
        ArrayList<ErrorField> errorFields = new ArrayList<>();
        ArrayList<String> policyIds = new ArrayList<>();
        String[] references = new String[3];

        try {

            // temp objects
            Object errorFieldsTemp = jsonObject.get(Fields.ERROR_FIELDS.toString());
            Object policyIdsTemp = jsonObject.get(Fields.POLICY_IDS.toString());

            if (errorFieldsTemp instanceof JSONArray) {
                for (Object field: (JSONArray) errorFieldsTemp)
                    errorFields.add(ErrorField.parseJson((JSONObject) field));
            }

            if (policyIdsTemp instanceof JSONArray) {
                for (Object policyId: (JSONArray) policyIdsTemp)
                    policyIds.add((String) policyId);
            }

            references[0] = jsonObject.getString(Fields.REF1.toString());
            references[1] = jsonObject.getString(Fields.REF2.toString());
            references[2] = jsonObject.getString(Fields.REF3.toString());

            return new Application(
                    jsonObject.getString(Fields.ID.toString()),
                    jsonObject.getJSONObject(Fields.FORM_DATA.toString()),
                    ApplicationStatus.valueOf(jsonObject.getString(Fields.STATUS.toString())),
                    jsonObject.getDouble(Fields.AMOUNT.toString()),
                    jsonObject.getDouble(Fields.AMOUNT_WITH_TAX.toString()),
                    ApplicationSource.valueOf(jsonObject.getString(Fields.SOURCE.toString())),
                    references,
                    jsonObject.getString(Fields.INSURER_PACKAGE_CODE.toString()),
                    jsonObject.getString(Fields.INSURER_PACKAGE_NAME.toString()),
                    jsonObject.getString(Fields.INSURER_APPLICATION_NO.toString()),
                    jsonObject.getString(Fields.LANGUAGE.toString()),
                    dateFormat.parse(jsonObject.getString(Fields.CREATED_AT.toString())),
                    dateFormat.parse(jsonObject.getString(Fields.UPDATED_AT.toString())),
                    jsonObject.getString(Fields.PRODUCT_ID.toString()),
                    jsonObject.getString(Fields.USER_ID.toString()),
                    jsonObject.getString(Fields.TEAM_ID.toString()),
                    errorFields,
                    jsonObject.getString(Fields.ERROR_MESSAGE.toString()),
                    policyIds);
        } catch (JSONException e) {
            throw new AcrosureException("Malformed responded JSON", 1);
        }
    }

    public String id() {
        return id;
    }

    public JSONObject data() {
        return data;
    }

    public ApplicationStatus status() {
        return status;
    }

    public double amount() {
        return amount;
    }

    public double amountWithTax() {
        return amountWithTax;
    }

    public ApplicationSource source() {
        return source;
    }

    public String reference(int index) {
        return references[index - 1];
    }

    public String insurerPackageCode() {
        return insurerPackageCode;
    }

    public String insurerPackageName() {
        return insurerPackageName;
    }

    public String insurerApplicationNo() {
        return insurerApplicationNo;
    }

    public String language() {
        return language;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Date updatedAt() {
        return updatedAt;
    }

    public String productId() {
        return productId;
    }

    public String userId() {
        return userId;
    }

    public String teamId() {
        return teamId;
    }

    public ArrayList<ErrorField> errorFields() {
        return errorFields;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public ArrayList<String> policyIds() {
        return policyIds;
    }

    public void setPackage(InsurancePackage insurancePackage) {
        this.amount = insurancePackage.amount();
        this.amountWithTax = insurancePackage.amountWithTax();
        this.insurerPackageCode = insurancePackage.insurerPackageCode();
        this.insurerPackageName = insurancePackage.name();
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                ", amountWithTax=" + amountWithTax +
                ", insurerPackageCode='" + insurerPackageCode + '\'' +
                ", insurerPackageName='" + insurerPackageName + '\'' +
                ", insurerApplicationNo='" + insurerApplicationNo + '\'' +
                ", productId='" + productId + '\'' +
                ", policyIds=" + policyIds +
                '}';
    }

    public enum Fields {
        ID("id"),
        APPLICATION_ID("application_id"),
        FORM_DATA("form_data"),
        STATUS("status"),
        AMOUNT("amount"),
        AMOUNT_WITH_TAX("amount_with_tax"),
        SOURCE("source"),
        REF1("ref1"),
        REF2("ref2"),
        REF3("ref3"),
        INSURER_PACKAGE_CODE("insurer_package_code"),
        INSURER_PACKAGE_NAME("insurer_package_name"),
        INSURER_APPLICATION_NO("insurer_application_no"),
        LANGUAGE("language"),
        CREATED_AT("created_at"),
        UPDATED_AT("updated_at"),
        PRODUCT_ID("product_id"),
        USER_ID("user_id"),
        TEAM_ID("team_id"),
        ERROR_FIELDS("error_fields"),
        ERROR_MESSAGE("error_message"),
        POLICY_IDS("policy_ids");

        private final String field;

        Fields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }
}
