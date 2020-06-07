package com.springbootreactjsjwtauth.entity.jwt;

public class ErrorResponse {

    private final boolean status;
    private final String message;
    // private final Date timestamp;

    protected ErrorResponse(final boolean status, final String message) {
        this.status = status;
        this.message = message;
        // this.timestamp = new java.util.Date();
    }

    public static ErrorResponse of(final boolean status, final String message) {
        return new ErrorResponse(status, message);
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // public Date getTimestamp() {
    // return timestamp;
    // }

}
