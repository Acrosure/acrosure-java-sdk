package com.acrosure;

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
}