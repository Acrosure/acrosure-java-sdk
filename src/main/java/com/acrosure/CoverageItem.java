package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoverageItem {
    private String key;
    private String detail;
    private String fullDetail;
    private String category;
    private String valueText;
    private double valueNumber;
    private String valueUnit;
    private boolean main;

    @JsonCreator
    CoverageItem(
            @JsonProperty("key") String key,
            @JsonProperty("detail") String detail,
            @JsonProperty("full_detail") String fullDetail,
            @JsonProperty("category") String category,
            @JsonProperty("value_text") String valueText,
            @JsonProperty("value_number") double valueNumber,
            @JsonProperty("value_unit") String valueUnit,
            @JsonProperty("main") boolean main) {
        this.key = key;
        this.detail = detail;
        this.fullDetail = fullDetail;
        this.category = category;
        this.valueText = valueText;
        this.valueNumber = valueNumber;
        this. valueUnit = valueUnit;
        this.main = main;
    }

    public String getKey() {
        return key;
    }

    public String getDetail() {
        return detail;
    }

    public String getFullDetail() {
        return fullDetail;
    }

    public String getCategory() {
        return category;
    }

    public String getValueText() {
        return valueText;
    }

    public double getValueNumber() {
        return valueNumber;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public boolean isMain() {
        return main;
    }

    @Override
    public String toString() {
        return "CoverageItem{" +
                "key='" + key + '\'' +
                ", detail='" + detail + '\'' +
                ", fullDetail='" + fullDetail + '\'' +
                ", category='" + category + '\'' +
                ", valueText='" + valueText + '\'' +
                ", valueNumber=" + valueNumber +
                ", valueUnit='" + valueUnit + '\'' +
                ", canHide=" + main +
                '}';
    }
}
