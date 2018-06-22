package com.acrosure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    private ArrayList<String> errorFields;
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
            ArrayList<String> errorFields,
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

    void setErrorFields(ArrayList<String> errorFields) {
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
        ArrayList<String> errorFields, policyIds;
        String[] references = new String[3];

        // temp objects
        JSONArray errorFieldsTemp = (JSONArray) jsonObject.get(Fields.ERROR_FIELDS.toString());
        JSONArray policyIdsTemp = (JSONArray) jsonObject.get(Fields.POLICY_IDS.toString());

        errorFields = policyIds = new ArrayList<>();

        references[0] = (String) jsonObject.get(Fields.REF1.toString());
        references[1] = (String) jsonObject.get(Fields.REF2.toString());
        references[2] = (String) jsonObject.get(Fields.REF3.toString());

        if (errorFieldsTemp != null) {
            for (Object field: errorFieldsTemp)
                errorFields.add((String) field);
        }

        if (policyIdsTemp != null) {
            for (Object policyId: policyIdsTemp)
                policyIds.add((String) policyId);
        }

        try {
            return new Application(
                    (String) jsonObject.get(Fields.ID.toString()),
                    (JSONObject) jsonObject.get(Fields.FORM_DATA.toString()),
                    ApplicationStatus.valueOf((String) jsonObject.get(Fields.STATUS.toString())),
                    ((Number) jsonObject.get(Fields.AMOUNT.toString())).doubleValue(),
                    ((Number) jsonObject.get(Fields.AMOUNT_WITH_TAX.toString())).doubleValue(),
                    ApplicationSource.valueOf((String) jsonObject.get(Fields.SOURCE.toString())),
                    references,
                    (String) jsonObject.get(Fields.INSURER_PACKAGE_CODE.toString()),
                    (String) jsonObject.get(Fields.INSURER_PACKAGE_NAME.toString()),
                    (String) jsonObject.get(Fields.INSURER_APPLICATION_NO.toString()),
                    (String) jsonObject.get(Fields.LANGUAGE.toString()),
                    dateFormat.parse((String) jsonObject.get(Fields.CREATED_AT.toString())),
                    dateFormat.parse((String) jsonObject.get(Fields.UPDATED_AT.toString())),
                    (String) jsonObject.get(Fields.PRODUCT_ID.toString()),
                    (String) jsonObject.get(Fields.USER_ID.toString()),
                    (String) jsonObject.get(Fields.TEAM_ID.toString()),
                    errorFields,
                    (String) jsonObject.get(Fields.ERROR_MESSAGE.toString()),
                    policyIds);
        } catch (NullPointerException e) {
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

    public ArrayList<String> errorFields() {
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
