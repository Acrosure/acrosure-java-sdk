package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Package {
    private String packageName;
    private String packageCode;
    private String packageDescription;
    private CoverageItem[] coverageItems;
    private Premium premium;
    private Condition[] conditions;
    private JsonNode packageOptions;
    private JsonNode extra;

    @JsonCreator
    Package(
            @JsonProperty("package_name") String packageName,
            @JsonProperty("package_code") String packageCode,
            @JsonProperty("package_description") String packageDescription,
            @JsonProperty("coverage_items") CoverageItem[] coverageItems,
            @JsonProperty("premium") Premium premium,
            @JsonProperty("conditions") Condition[] conditions,
            @JsonProperty("package_options") JsonNode packageOptions,
            @JsonProperty("extra") JsonNode extra) {
        this.packageName = packageName;
        this.packageCode = packageCode;
        this.packageDescription = packageDescription;
        this.coverageItems = coverageItems;
        this.premium = premium;
        this.conditions = conditions;
        this.packageOptions = packageOptions;
        this.extra = extra;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public CoverageItem[] getCoverageItems() {
        return coverageItems;
    }

    public Premium getPremium() {
        return premium;
    }

    public Condition[] getConditions() {
        return conditions;
    }

    public JsonNode getPackageOptions() {
        return packageOptions;
    }

    public JsonNode getExtra() {
        return extra;
    }
}
