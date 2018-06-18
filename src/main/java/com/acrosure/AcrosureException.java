package com.acrosure;

public class AcrosureException extends Exception {
    private final int statusCode;

    AcrosureException(int statusCode) {
        super();
        this.statusCode = statusCode;
    }

    AcrosureException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    AcrosureException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    AcrosureException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
