package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Date;

public class Policy {
    private final String id;
    private final Date effectiveDate;
    private final Date expiryDate;
    private final String insurerPolicyCode;
    private final String insurerPolicyUrl;
    private final String policyUrl;
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
    private final Date confirmedAt;
    private final String applicationId;
    private final String teamId;
    private final String userId;
    private final String productId;
    private final JsonNode basicData;
    private final JsonNode packageOptions;
    private final JsonNode additionalData;
    private final String source;
    private final String packageCode;
    private final String errorMessage;
    private final String teamName;
    private final String signedPolicyUrl;
    private final String applicationNo;

    @JsonCreator
    Policy(
            @JsonProperty("id") String id,
            @JsonProperty("effective_date") Date effectiveDate,
            @JsonProperty("expiry_date") Date expiryDate,
            @JsonProperty("insurer_policy_code") String insurerPolicyCode,
            @JsonProperty("insurer_policy_url") String insurerPolicyUrl,
            @JsonProperty("policy_url") String policyUrl,
            @JsonProperty("amount") double amount,
            @JsonProperty("amount_with_tax") double amountWithTax,
            @JsonProperty("status") PolicyStatus status,
            @JsonProperty("insurer_id") String insurerId,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("telephone") String telephone,
            @JsonProperty("download_at") Date downloadAt,
            @JsonProperty("created_at") Date createdAt,
            @JsonProperty("confirmed_at") Date confirmedAt,
            @JsonProperty("application_id") String applicationId,
            @JsonProperty("team_id") String teamId,
            @JsonProperty("user_id") String userId,
            @JsonProperty("product_id") String productId,
            @JsonProperty("basic_data") JsonNode basicData,
            @JsonProperty("package_options") JsonNode packageOptions,
            @JsonProperty("additional_data") JsonNode additionalData,
            @JsonProperty("source") String source,
            @JsonProperty("package_code") String packageCode,
            @JsonProperty("error_message") String errorMessage,
            @JsonProperty("team_name") String teamName,
            @JsonProperty("signed_policy_url") String signedPolicyUrl,
            @JsonProperty("application_no") String applicationNo) {
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
        this.confirmedAt = confirmedAt;
        this.applicationId = applicationId;
        this.teamId = teamId;
        this.userId = userId;
        this.productId = productId;
        this.basicData = basicData;
        this.packageOptions = packageOptions;
        this.additionalData = additionalData;
        this.source = source;
        this.packageCode = packageCode;
        this.errorMessage = errorMessage;
        this.teamName = teamName;
        this.signedPolicyUrl = signedPolicyUrl;
        this.applicationNo = applicationNo;
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

    public String getInsurerPolicyUrl() {
        return insurerPolicyUrl;
    }

    public String getPolicyUrl() {
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

    public Date getConfirmedAt() {
        return confirmedAt;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public JsonNode getBasicData() {
        return basicData;
    }

    public JsonNode getPackageOptions() {
        return packageOptions;
    }

    public JsonNode getAdditionalData() {
        return additionalData;
    }

    public String getSource() {
        return source;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getSignedPolicyUrl() {
        return signedPolicyUrl;
    }

    public String getApplicationNo() {
        return applicationNo;
    }
}
