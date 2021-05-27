package com.ceeblue.streamingcloud.sdk.utils;

import com.ceeblue.streamingcloud.sdk.http.template.utils.HTTPMethod;

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
        return "ClientException: \n" +
                "api='" + api + '\n' +
                "Method=" + method + "\n" +
                "Exception=" + exception + "\n" +
                "Message='" + message + '\'' +
                '}';
    }
}

