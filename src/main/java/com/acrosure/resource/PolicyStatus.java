package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PolicyStatus {
    COMPLETED("COMPLETED"),
    INCOMPLETED("INCOMPLETED"),
    INSURER_COMPLETED("INSURER_COMPLETED"),
    CANCELED("CANCELED");

    private final String status;

    PolicyStatus(String status) {
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