package com.acrosure;

import org.json.simple.JSONObject;

public class ApiResponse {
    private final int httpStatus;
    private final JSONObject content;

    ApiResponse(int httpStatus, JSONObject content) {
        this.httpStatus = httpStatus;
        this.content = content;
    }

    int getHttpStatus() {
        return httpStatus;
    }

    JSONObject getContent() {
        return content;
    }
}
