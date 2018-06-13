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

    static Policy parseJson(JSONObject jsonObject, Application application) throws ParseException, MalformedURLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");
        String insurerPolicyUrl = (String) jsonObject.get("insurer_policy_url");
        String policyUrl = (String) jsonObject.get("policy_url");
        String signedPolicyUrl = (String) jsonObject.get("signed_policy_url");

        if (!application.getId().equals(jsonObject.get("application_id"))) {
            // @TODO Should I raise an error?
            return null;
        }

        return new Policy(
                (String) jsonObject.get("id"),
                dateFormat.parse((String) jsonObject.get("effective_date")),
                dateFormat.parse((String) jsonObject.get("expiry_date")),
                (String) jsonObject.get("insurer_policy_code"),
                insurerPolicyUrl.equals("") ? null : new URL(insurerPolicyUrl),
                policyUrl.equals("") ? null : new URL(policyUrl),
                (Double) jsonObject.get("amount"),
                (Long) jsonObject.get("amount_with_tax"),
                PolicyStatus.valueOf((String) jsonObject.get("status")),
                (String) jsonObject.get("insurer_id"),
                (String) jsonObject.get("first_name"),
                (String) jsonObject.get("last_name"),
                (String) jsonObject.get("email"),
                (String) jsonObject.get("telephone"),
                dateFormat.parse((String) jsonObject.get("download_at")),
                dateFormat.parse((String) jsonObject.get("created_at")),
                application,
                (String) jsonObject.get("team_id"),
                (String) jsonObject.get("user_id"),
                (JSONObject) jsonObject.get("form_data"),
                (String) jsonObject.get("source"),
                (String) jsonObject.get("insurer_package_code"),
                (String) jsonObject.get("error_message"),
                (String) jsonObject.get("team_name"),
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
}
