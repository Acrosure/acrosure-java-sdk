package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.Date;

@JsonIgnoreProperties({ "step" })
public class Application {
    /** system-defined fields */
    private String id;
    private String productId;
    private String applicationNo;
    private ApplicationStatus status;
    private ApplicationSource source;
    private String userId;
    private String teamId;
    private boolean paid;
    private double netPremium;
    private double grossPremium;
    private double vat;
    private double duty;
    private ApplicationPackageData packageData;
    private String[] policyIds;
    private ErrorField[] errorFields;
    private String errorMessage;
    private Date expiredAt;
    private Date createdAt;
    private Date updatedAt;

    /** user-defined fields */
    private ObjectNode basicData;
    private ObjectNode packageOptions;
    private ObjectNode additionalData;
    private String ref1;
    private String ref2;
    private String ref3;
    private String packageCode;
    private String groupPolicyId;

    @JsonCreator
    Application(
            @JsonProperty("id") String id,
            @JsonProperty("product_id") String productId,
            @JsonProperty("application_no") String applicationNo,
            @JsonProperty("status") ApplicationStatus status,
            @JsonProperty("source") ApplicationSource source,
            @JsonProperty("user_id") String userId,
            @JsonProperty("team_id") String teamId,
            @JsonProperty("paid") boolean paid,
            @JsonProperty("net_premium") double netPremium,
            @JsonProperty("gross_premium") double grossPremium,
            @JsonProperty("vat") double vat,
            @JsonProperty("duty") double duty,
            @JsonProperty("package_data") ApplicationPackageData packageData,
            @JsonProperty("policy_ids") String[] policyIds,
            @JsonProperty("error_fields") ErrorField[] errorFields,
            @JsonProperty("error_message") String errorMessage,
            @JsonProperty("expired_at") Date expiredAt,
            @JsonProperty("created_at") Date createdAt,
            @JsonProperty("updated_at") Date updatedAt,
            @JsonProperty("basic_data") ObjectNode basicData,
            @JsonProperty("package_options") ObjectNode packageOptions,
            @JsonProperty("additional_data") ObjectNode additionalData,
            @JsonProperty("ref1") String ref1,
            @JsonProperty("ref2") String ref2,
            @JsonProperty("ref3") String ref3,
            @JsonProperty("package_code") String packageCode,
            @JsonProperty("group_policy_id") String groupPolicyId) {
        this.id = id;
        this.productId = productId;
        this.applicationNo = applicationNo;
        this.status = status;
        this.source = source;
        this.userId = userId;
        this.teamId = teamId;
        this.paid = paid;
        this.netPremium = netPremium;
        this.grossPremium = grossPremium;
        this.vat = vat;
        this.duty = duty;
        this.packageData = packageData;
        this.policyIds = policyIds;
        this.errorFields = errorFields;
        this.errorMessage = errorMessage;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.basicData = basicData;
        this.packageOptions = packageOptions;
        this.additionalData = additionalData;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.packageCode = packageCode;
        this.groupPolicyId = groupPolicyId;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public ApplicationSource getSource() {
        return source;
    }

    public String getUserId() {
        return userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public boolean isPaid() {
        return paid;
    }

    public double getNetPremium() {
        return netPremium;
    }

    public double getGrossPremium() {
        return grossPremium;
    }

    public double getVat() {
        return vat;
    }

    public double getDuty() {
        return duty;
    }

    public ApplicationPackageData getPackageData() {
        return packageData;
    }

    public String[] getPolicyIds() {
        return policyIds;
    }

    public ErrorField[] getErrorFields() {
        return errorFields;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public ObjectNode getBasicData() {
        return basicData;
    }

    public ObjectNode getPackageOptions() {
        return packageOptions;
    }

    public ObjectNode getAdditionalData() {
        return additionalData;
    }

    public String getRef1() {
        return ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public String getRef3() {
        return ref3;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getGroupPolicyId() {
        return groupPolicyId;
    }

    /** user-defined fields */
    public void setBasicData(ObjectNode basicData) {
        this.basicData = basicData;
    }

    public void setPackageOptions(ObjectNode packageOptions) {
        this.packageOptions = packageOptions;
    }

    public void setAdditionalData(ObjectNode additionalData) {
        this.additionalData = additionalData;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public void setRef3(String ref3) {
        this.ref3 = ref3;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public void setGroupPolicyId(String groupPolicyId) {
        this.groupPolicyId = groupPolicyId;
    }

    /** copy method */
    Application copy(Application origin) {
        this.applicationNo = origin.applicationNo;
        this.createdAt = origin.createdAt;
        this.duty = origin.duty;
        this.errorFields = origin.errorFields;
        this.errorMessage = origin.errorMessage;
        this.expiredAt = origin.expiredAt;
        this.grossPremium = origin.grossPremium;
        this.netPremium = origin.netPremium;
        this.packageData = origin.packageData;
        this.paid = origin.paid;
        this.policyIds = origin.policyIds;
        this.source = origin.source;
        this.status = origin.status;
        this.teamId = origin.teamId;
        this.updatedAt = origin.updatedAt;
        this.userId = origin.userId;
        this.vat = origin.vat;

        return this;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", status=" + status +
                ", source=" + source +
                ", userId='" + userId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", paid=" + paid +
                ", netPremium=" + netPremium +
                ", grossPremium=" + grossPremium +
                ", vat=" + vat +
                ", duty=" + duty +
                ", packageData=" + packageData +
                ", policyIds=" + Arrays.toString(policyIds) +
                ", errorFields=" + Arrays.toString(errorFields) +
                ", errorMessage='" + errorMessage + '\'' +
                ", expiredAt=" + expiredAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", basicData=" + basicData +
                ", packageOptions=" + packageOptions +
                ", additionalData=" + additionalData +
                ", ref1='" + ref1 + '\'' +
                ", ref2='" + ref2 + '\'' +
                ", ref3='" + ref3 + '\'' +
                ", packageCode='" + packageCode + '\'' +
                '}';
    }
}
