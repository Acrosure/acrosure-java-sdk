package com.acrosure.resource;

public enum ApplicationSource {
    PARTNER("PARTNER"),
    CUSTOMER("CUSTOMER");

    private final String source;

    ApplicationSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return source;
    }
}
