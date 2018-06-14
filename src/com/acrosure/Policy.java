package com.acrosure;

import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Policy {
    private final String id;
    private final Date effectiveDate;
    private final Date expiryDate;
    private final String insurerPolicyCode;
    private final URL insurerPolicyUrl;
    private final URL policyUrl;
    private final double amount;
    private final double amountWithTax;
    private final PolicyStatus status;
    private final String insurerId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String telephone;
    private final Date downloadAt;
    private final Date createdAt;
    private final Application application;
    private final String teamId;
    private final String userId;
    private final JSONObject data;
    private final String source;
    private final String insurerPackageCode;
    private final String errorMessage;
    private final String teamName;
    private final URL signedPolicyUrl;

    Policy(String id,
           Date effectiveDate,
           Date expiryDate,
           String insurerPolicyCode,
           URL insurerPolicyUrl,
           URL policyUrl,
           double amount,
           double amountWithTax,
           PolicyStatus status,
           String insurerId,
           String firstName,
           String lastName,
           String email,
           String telephone,
           Date downloadAt,
           Date createdAt,
           Application application,
           String teamId,
           String userId,
           JSONObject data,
           String source,
           String insurerPackageCode,
           String errorMessage,
           String teamName,
           URL signedPolicyUrl) {
        this.id = id;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
        this.insurerPolicyCode = insurerPolicyCode;
        this.insurerPolicyUrl = insurerPolicyUrl;
        this.policyUrl = policyUrl;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
        this.status = status;
        this.insurerId = insurerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.downloadAt = downloadAt;
        this.createdAt = createdAt;
        this.application = application;
        this.teamId = teamId;
        this.userId = userId;
        this.data = data;
        this.source = source;
        this.insurerPackageCode = insurerPackageCode;
        this.errorMessage = errorMessage;
        this.teamName = teamName;
        this.signedPolicyUrl = signedPolicyUrl;
    }

    public String getId() {
        return id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getInsurerPolicyCode() {
        return insurerPolicyCode;
    }

    public URL getInsurerPolicyUrl() {
        return insurerPolicyUrl;
    }

    public URL getPolicyUrl() {
        return policyUrl;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountWithTax() {
        return amountWithTax;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public String getInsurerId() {
        return insurerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getDownloadAt() {
        return downloadAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Application getApplication() {
        return application;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getUserId() {
        return userId;
    }

    public JSONObject getData() {
        return data;
    }

    public String getSource() {
        return source;
    }

    public String getInsurerPackageCode() {
        return insurerPackageCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTeamName() {
        return teamName;
    }

    public URL getSignedPolicyUrl() {
        return signedPolicyUrl;
    }

    static Policy parseJson(JSONObject jsonObject, Application application)
            throws ParseException, MalformedURLException, AcrosureException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");
        String insurerPolicyUrl = (String) jsonObject.get(Fields.INSURER_POLICY_URL.toString());
        String policyUrl = (String) jsonObject.get(Fields.POLICY_URL.toString());
        String signedPolicyUrl = (String) jsonObject.get(Fields.SIGNED_POLICY_URL.toString());
        String applicationId = (String) jsonObject.get(Fields.APPLICATION_ID.toString());

        if (!application.getId().equals(applicationId)) {
            throw new AcrosureException(
                    "Unexpected application ID: " + applicationId + ". Expected " + application.getId(), 500);
        }

        return new Policy(
                (String) jsonObject.get(Fields.ID.toString()),
                dateFormat.parse((String) jsonObject.get(Fields.EFFECTIVE_DATE.toString())),
                dateFormat.parse((String) jsonObject.get(Fields.EXPIRY_DATE.toString())),
                (String) jsonObject.get(Fields.INSURER_POLICY_CODE.toString()),
                insurerPolicyUrl.equals("") ? null : new URL(insurerPolicyUrl),
                policyUrl.equals("") ? null : new URL(policyUrl),
                (Double) jsonObject.get(Fields.AMOUNT.toString()),
                (Long) jsonObject.get(Fields.AMOUNT_WITH_TAX.toString()),
                PolicyStatus.valueOf((String) jsonObject.get(Fields.STATUS.toString())),
                (String) jsonObject.get(Fields.INSURER_ID.toString()),
                (String) jsonObject.get(Fields.FIRST_NAME.toString()),
                (String) jsonObject.get(Fields.LAST_NAME.toString()),
                (String) jsonObject.get(Fields.EMAIL.toString()),
                (String) jsonObject.get(Fields.TELEPHONE.toString()),
                dateFormat.parse((String) jsonObject.get(Fields.DOWNLOAD_AT.toString())),
                dateFormat.parse((String) jsonObject.get(Fields.CREATED_AT.toString())),
                application,
                (String) jsonObject.get(Fields.TEAM_ID.toString()),
                (String) jsonObject.get(Fields.USER_ID.toString()),
                (JSONObject) jsonObject.get(Fields.FORM_DATA.toString()),
                (String) jsonObject.get(Fields.SOURCE.toString()),
                (String) jsonObject.get(Fields.INSURER_PACKAGE_CODE),
                (String) jsonObject.get(Fields.ERROR_MESSAGE.toString()),
                (String) jsonObject.get(Fields.TEAM_NAME),
                signedPolicyUrl.equals("") ? null : new URL(signedPolicyUrl));
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id='" + id + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", expiryDate=" + expiryDate +
                ", amount=" + amount +
                ", amountWithTax=" + amountWithTax +
                ", status='" + status + '\'' +
                ", application=" + application +
                '}';
    }

    public enum Fields {
        ID("id"),
        EFFECTIVE_DATE("effective_date"),
        EXPIRY_DATE("expiry_date"),
        INSURER_POLICY_CODE("insurer_policy_code"),
        INSURER_POLICY_URL("insurer_policy_url"),
        POLICY_URL("policy_url"),
        AMOUNT("amount"),
        AMOUNT_WITH_TAX("amount_with_tax"),
        STATUS("status"),
        INSURER_ID("insurer_id"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        EMAIL("email"),
        TELEPHONE("telephone"),
        DOWNLOAD_AT("download_at"),
        CREATED_AT("created_at"),
        TEAM_ID("team_id"),
        USER_ID("user_id"),
        FORM_DATA("form_data"),
        SOURCE("source"),
        INSURER_PACKAGE_CODE("insurer_package_code"),
        ERROR_MESSAGE("error_message"),
        TEAM_NAME("team_name"),
        SIGNED_POLICY_URL("signed_policy_url"),
        APPLICATION_ID("application_id");

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
