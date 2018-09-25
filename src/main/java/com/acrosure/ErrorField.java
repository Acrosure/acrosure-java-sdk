package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorField {
    private final String field;
    private final String errorMessage;

    @JsonCreator
    ErrorField(
            @JsonProperty("field") String field,
            @JsonProperty("error_message") String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorField{" +
                "field='" + field + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
