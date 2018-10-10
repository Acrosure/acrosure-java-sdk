package com.acrosure.form;

import com.acrosure.resource.ApplicationSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApplicationCreateForm {
    private String productId;
    private ObjectNode basicData;
    private ObjectNode packageOptions;
    private ObjectNode additionalData;
    private final ApplicationSource source = ApplicationSource.PARTNER;
    private String packageCode;
    private String groupPolicyId;
    private String ref1;
    private String ref2;
    private String ref3;

    public ApplicationCreateForm() {
        this.productId = "";
        this.packageCode = "";
        this.groupPolicyId = "";
        this.ref1 = "";
        this.ref2 = "";
        this.ref3 = "";
    }

    public String getProductId() {
        return productId;
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

    public ApplicationSource getSource() {
        return source;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getGroupPolicyId() {
        return groupPolicyId;
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

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setBasicData(ObjectNode basicData) {
        this.basicData = basicData;
    }

    public void setPackageOptions(ObjectNode packageOptions) {
        this.packageOptions = packageOptions;
    }

    public void setAdditionalData(ObjectNode additionalData) {
        this.additionalData = additionalData;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public void setGroupPolicyId(String groupPolicyId) {
        this.groupPolicyId = groupPolicyId;
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
}
