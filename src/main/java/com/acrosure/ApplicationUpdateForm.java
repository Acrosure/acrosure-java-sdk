package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApplicationUpdateForm {
    private String id;
    private String packageCode;
    private ObjectNode basicData;
    private ObjectNode packageOptions;
    private ObjectNode additionalData;
    private String groupPolicyId;
    private String ref1;
    private String ref2;
    private String ref3;

    @JsonCreator
    ApplicationUpdateForm(
            @JsonProperty("id") String id,
            @JsonProperty("package_code") String packageCode,
            @JsonProperty("basic_data") ObjectNode basicData,
            @JsonProperty("package_options") ObjectNode packageOptions,
            @JsonProperty("additional_data") ObjectNode additionalData,
            @JsonProperty("group_policy_id") String groupPolicyId,
            @JsonProperty("ref1") String ref1,
            @JsonProperty("ref2") String ref2,
            @JsonProperty("ref3") String ref3) {
        this.id = id;
        this.packageCode = packageCode;
        this.basicData = basicData;
        this.packageOptions = packageOptions;
        this.additionalData = additionalData;
        this.groupPolicyId = groupPolicyId;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
    }

    @JsonProperty("application_id")
    public String getId() {
        return id;
    }

    public String getPackageCode() {
        return packageCode;
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
}
