package com.acrosure;

import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
    private final String id;
    private final JSONObject data;
    private ApplicationStatus status;
    private InsurancePackage packageData;
    private ApplicationSource source;
//    private String[] references;
    private String insurerApplicationNo;
    private String language;
    private Date createdAt;
    private Date updatedAt;
    private final String productId;
    private String userId;
    private String teamId;
//    private String productCode;
//    private String[] errorFields;
//    private String errorMessage;
//    private String[] policyIds;

    Application(
            String id,
            JSONObject obj,
            ApplicationStatus status,
            ApplicationSource source,
            String language,
            Date createdAt,
            Date updatedAt,
            String productId,
            String userId,
            String teamId) {
        this.id = id;
        this.data = obj;
        this.status = status;
        this.source = source;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productId = productId;
        this.userId = userId;
        this.teamId = teamId;
        packageData = null;
    }

    Application(
            String id,
            JSONObject obj,
            ApplicationSource source,
            String language,
            Date createdAt,
            Date updatedAt,
            String productId,
            String userId,
            String teamId) {
        this(id, obj, ApplicationStatus.INITIAL, source, language, createdAt, updatedAt, productId, userId, teamId);
    }

    void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    void setInsurerApplicationNo(String insurerApplicationNo) {
        this.insurerApplicationNo = insurerApplicationNo;
    }

    static Application parseJson(JSONObject jsonObject) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");

        return new Application(
                (String) jsonObject.get(Fields.ID.toString()),
                (JSONObject) jsonObject.get(Fields.FORM_DATA.toString()),
                ApplicationStatus.valueOf((String) jsonObject.get(Fields.STATUS.toString())),
                ApplicationSource.valueOf((String) jsonObject.get(Fields.SOURCE.toString())),
                (String) jsonObject.get(Fields.LANGUAGE.toString()),
                dateFormat.parse((String) jsonObject.get(Fields.CREATED_AT.toString())),
                dateFormat.parse((String) jsonObject.get(Fields.UPDATED_AT.toString())),
                (String) jsonObject.get(Fields.PRODUCT_ID.toString()),
                (String) jsonObject.get(Fields.USER_ID.toString()),
                (String) jsonObject.get(Fields.TEAM_ID.toString()));
    }

    public String getId() {
        return id;
    }

    public JSONObject data() {
        return data;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public InsurancePackage getPackageData() {
        return packageData;
    }

    public ApplicationSource getSource() {
        return source;
    }

    public String getInsurerApplicationNo() {
        return insurerApplicationNo;
    }

    public String getLanguage() {
        return language;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setPackage(InsurancePackage insurancePackage) {
        this.packageData = insurancePackage;
    }

    @Override
    public String toString() {
        return "Application{" +
                "productId='" + productId + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                ", packageData=" + packageData +
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
        INSURER_PACKAGE_CODE("insurer_package_code"),
        INSURER_PACKAGE_NAME("insurer_package_name"),
        INSURER_APPLICATION_NO("insurer_application_no"),
        LANGUAGE("language"),
        CREATED_AT("created_at"),
        UPDATED_AT("updated_at"),
        PRODUCT_ID("product_id"),
        USER_ID("user_id"),
        TEAM_ID("team_id");

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
