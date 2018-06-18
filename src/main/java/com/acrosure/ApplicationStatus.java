package com.acrosure;

public enum ApplicationStatus {
    INITIAL("INITIAL"),
    READY("READY"),
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