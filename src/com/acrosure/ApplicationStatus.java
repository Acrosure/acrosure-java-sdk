package com.acrosure;

public enum ApplicationStatus {
    INITIAL("INITIAL"),
    READY("READY"),
    COMPLETED("COMPLETED");

    private final String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}