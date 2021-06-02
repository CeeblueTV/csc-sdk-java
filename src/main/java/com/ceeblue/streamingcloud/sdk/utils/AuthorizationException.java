package com.ceeblue.streamingcloud.sdk.utils;

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
