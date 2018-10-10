package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationStatus {
    INITIAL("INITIAL"),
    PACKAGE_REQUIRED("PACKAGE_REQUIRED"),
    DATA_REQUIRED("DATA_REQUIRED"),
    READY("READY"),
    SUBMITTED("SUBMITTED"),
    CONFIRMING("CONFIRMING"),
    AWAIT_POLICY("AWAIT_POLICY"),
    CANCELED("CANCELED"),
    EXPIRED("EXPIRED"),
    COMPLETED("COMPLETED"),
    RENEWED("RENEWED");

    private final String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}