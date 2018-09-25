package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class ApplicationPackageData {
    private CoverageItem[] coverageItems;
    private String packageCode;
    private JsonNode premiumDetail;
    private JsonNode packageOptions;

    @JsonCreator
    ApplicationPackageData(
            @JsonProperty("coverage_items") CoverageItem[] coverageItems,
            @JsonProperty("package_code") String packageCode,
            @JsonProperty("premium_detail") JsonNode premiumDetail,
            @JsonProperty("package_options") JsonNode packageOptions) {
        this.coverageItems = coverageItems;
        this.packageCode = packageCode;
        this.premiumDetail = premiumDetail;
        this.packageOptions = packageOptions;
    }

    public CoverageItem[] getCoverageItems() {
        return coverageItems;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public JsonNode getPremiumDetail() {
        return premiumDetail;
    }

    public JsonNode getPackageOptions() {
        return packageOptions;
    }
}
