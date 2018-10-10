package com.acrosure.form;

public class PolicyQuery {
    private long offset;
    private long limit;
    private String orderBy;
    private boolean desc;
    private String status;
    private String source;
    private String productId;
    private String teamId;
    private String query;
    private String basicData;
    private String packageOptions;
    private String additionalData;
    private String ref1;
    private String ref2;
    private String ref3;
    private String applicationNo;
    private String insurerPolicyCode;
    private String startCreatedDate;
    private String endCreatedDate;
    private String startEffectiveDate;
    private String endEffectiveDate;
    private String startExpiryDate;
    private String endExpiryDate;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private boolean partialMatch;

    public PolicyQuery() {
        this.orderBy = "";
        this.status = "";
        this.source = "";
        this.productId = "";
        this.teamId = "";
        this.query = "";
        this.basicData = "";
        this.packageOptions = "";
        this.additionalData = "";
        this.ref1 = "";
        this.ref2 = "";
        this.ref3 = "";
        this.applicationNo = "";
        this.insurerPolicyCode = "";
        this.startCreatedDate = "";
        this.endCreatedDate = "";
        this.startEffectiveDate = "";
        this.endEffectiveDate = "";
        this.startExpiryDate = "";
        this.endExpiryDate = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.telephone = "";
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getBasicData() {
        return basicData;
    }

    public void setBasicData(String basicData) {
        this.basicData = basicData;
    }

    public String getPackageOptions() {
        return packageOptions;
    }

    public void setPackageOptions(String packageOptions) {
        this.packageOptions = packageOptions;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getRef3() {
        return ref3;
    }

    public void setRef3(String ref3) {
        this.ref3 = ref3;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getInsurerPolicyCode() {
        return insurerPolicyCode;
    }

    public void setInsurerPolicyCode(String insurerPolicyCode) {
        this.insurerPolicyCode = insurerPolicyCode;
    }

    public String getStartCreatedDate() {
        return startCreatedDate;
    }

    public void setStartCreatedDate(String startCreatedDate) {
        this.startCreatedDate = startCreatedDate;
    }

    public String getEndCreatedDate() {
        return endCreatedDate;
    }

    public void setEndCreatedDate(String endCreatedDate) {
        this.endCreatedDate = endCreatedDate;
    }

    public String getStartEffectiveDate() {
        return startEffectiveDate;
    }

    public void setStartEffectiveDate(String startEffectiveDate) {
        this.startEffectiveDate = startEffectiveDate;
    }

    public String getEndEffectiveDate() {
        return endEffectiveDate;
    }

    public void setEndEffectiveDate(String endEffectiveDate) {
        this.endEffectiveDate = endEffectiveDate;
    }

    public String getStartExpiryDate() {
        return startExpiryDate;
    }

    public void setStartExpiryDate(String startExpiryDate) {
        this.startExpiryDate = startExpiryDate;
    }

    public String getEndExpiryDate() {
        return endExpiryDate;
    }

    public void setEndExpiryDate(String endExpiryDate) {
        this.endExpiryDate = endExpiryDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }
}
