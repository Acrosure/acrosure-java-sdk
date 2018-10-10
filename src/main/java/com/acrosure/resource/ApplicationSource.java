package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationSource {
    PARTNER("PARTNER"),
    CUSTOMER("CUSTOMER"),
    NONE("");

    private final String source;

    ApplicationSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return source;
    }

    @JsonValue
    public String getSource() {
        return source;
    }
}
