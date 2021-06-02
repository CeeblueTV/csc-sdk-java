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

    public ClientException(String api, String message) {
        this.api = api;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClientException: \n" +
                (api != null ? "api='" + api : "") + '\n' +
                (method != null ? "Method=" + method : "") + "\n" +
                (exception != null ? "Exception=" + exception : "") + "\n" +
                (message != null ? "Message='" + message : "") + '\'' +
                '}';
    }
}

