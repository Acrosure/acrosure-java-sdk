package com.acrosure.resource;

public enum ApplicationStatus {
    INITIAL("INITIAL"),
    PACKAGE_REQUIRED("PACKAGE_REQUIRED"),
    DATA_REQUIRED("DATA_REQUIRED"),
    READY("READY"),
    SUBMITTED("SUBMITTED"),
    AWAIT_POLICY("AWAIT_POLICY"),
    CANCELED("CANCELED"),
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
}