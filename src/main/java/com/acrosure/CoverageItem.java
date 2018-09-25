package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoverageItem {
    private String key;
    private String detail;
    private String fullDetail;
    private String category;
    private double value;
    private boolean canHide;

    @JsonCreator
    CoverageItem(
            @JsonProperty("key") String key,
            @JsonProperty("detail") String detail,
            @JsonProperty("full_detail") String fullDetail,
            @JsonProperty("category") String category,
            @JsonProperty("value") double value,
            @JsonProperty("can_hide") boolean canHide) {
        this.key = key;
        this.detail = detail;
        this.fullDetail = fullDetail;
        this.category = category;
        this.value = value;
        this.canHide = canHide;
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

    public double getValue() {
        return value;
    }

    public boolean isCanHide() {
        return canHide;
    }
}
