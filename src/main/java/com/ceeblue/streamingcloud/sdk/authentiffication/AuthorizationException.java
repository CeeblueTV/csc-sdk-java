package com.ceeblue.streamingcloud.sdk.authentiffication;

public class AuthorizationException extends RuntimeException {

    private final String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthorizationException{" +
                "message='" + message + '\'' +
                '}';
    }
}
