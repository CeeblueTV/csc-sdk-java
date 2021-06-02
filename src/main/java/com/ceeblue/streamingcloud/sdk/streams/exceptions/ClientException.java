package com.ceeblue.streamingcloud.sdk.streams.exceptions;

import com.ceeblue.streamingcloud.sdk.http.HTTPMethod;

public class ClientException extends RuntimeException {

    private final String api;

    private final String message;

    private HTTPMethod method;

    private RuntimeException exception;

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

