package com.ceeblue.streamingcloud.sdk.authentiffication;

public class AuthorizationException extends RuntimeException {

    String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthorizationException{" +
                "message='" + message + '\'' +
                '}';
    }
}
