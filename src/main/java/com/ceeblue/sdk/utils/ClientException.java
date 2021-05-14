package com.ceeblue.sdk.utils;

import com.ceeblue.sdk.http.template.HTTPMethod;

public class ClientException extends RuntimeException {
    String api;
    HTTPMethod method;
    RuntimeException exception;
    String message;

    public ClientException(String message, String api, HTTPMethod method, RuntimeException exception) {
        this.message = message;
        this.api = api;
        this.method = method;
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "api='" + api + '\'' +
                ", method=" + method +
                ", exception=" + exception +
                ", message='" + message + '\'' +
                '}';
    }
}

