package com.acrosure;

import org.json.JSONObject;

public class ErrorField {
    private final String field;
    private final String errorMessage;

    ErrorField(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    static ErrorField parseJson(JSONObject jsonObject) {
        return new ErrorField(
                jsonObject.getString(Fields.FIELD.toString()),
                jsonObject.getString(Fields.ERROR_MESSAGE.toString()));
    }

    @Override
    public String toString() {
        return "ErrorField{" +
                "field='" + field + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public enum Fields {
        FIELD("field"),
        ERROR_MESSAGE("error_message");

        private final String field;

        Fields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }
}
