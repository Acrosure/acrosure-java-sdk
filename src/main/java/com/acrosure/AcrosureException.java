package com.acrosure;

/**
 * AcrosureException holds information of all the errors sent by
 * the Acrosure's web APIs.
 */
public class AcrosureException extends Exception {
    private final int statusCode;

    /**
     * Initiates an AcrosureException instance with an HTTP status code.
     * The code is most likely to be 400 or 500.
     *
     * @param statusCode an HTTP status code
     */
    AcrosureException(int statusCode) {
        super();
        this.statusCode = statusCode;
    }

    /**
     * Initiates an AcrosureException instance with an HTTP status code and
     * an associated message.
     *
     * @param message       the error message from the server
     * @param statusCode    an HTTP status code
     */
    AcrosureException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Initiates an AcrosureException instance with an HTTP status code,
     * an associated message, and the underlying, original exception.
     * This is most likely not going to be used.
     *
     * @param message       the error message from the server
     * @param cause         the underlying, original exception
     * @param statusCode    an HTTP status code
     */
    AcrosureException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    /**
     * Initiates an AcrosureException instance with an HTTP status code
     * and the underlying, original exception.
     *
     * @param cause         the underlying, original exception
     * @param statusCode    an HTTP status code
     */
    AcrosureException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    /**
     * Returns the HTTP status code returned by the server
     *
     * @return the HTTP status code returned by the server
     */
    public int getStatusCode() {
        return statusCode;
    }
}
