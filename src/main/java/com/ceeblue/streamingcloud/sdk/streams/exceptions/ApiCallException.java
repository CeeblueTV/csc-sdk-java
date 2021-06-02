package com.ceeblue.streamingcloud.sdk.streams.exceptions;

import com.ceeblue.streamingcloud.sdk.http.HTTPMethod;

public class ApiCallException extends RuntimeException {

    /**
     * Response code of server or -1 in timeout case
     */
    public final int exceptionCode;

    /**
     * Server response
     */
    public String serverResponse;

    /**
     * API that was called
     */
    public String api;

    public HTTPMethod method;

    private String message;

    public ApiCallException(String message, int exceptionCode, String serverResponse) {
        this.exceptionCode = exceptionCode;
        this.message = message;
        this.serverResponse = serverResponse;
    }

    public ApiCallException(String message, int exceptionCode, String serverResponse, String api, HTTPMethod method) {
        this(message, exceptionCode, serverResponse);
        this.api = api;
        this.method = method;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public ApiCallException setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ApiCallException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getApi() {
        return api;
    }

    public ApiCallException setApi(String api) {
        this.api = api;
        return this;
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public ApiCallException setMethod(HTTPMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public String toString() {
        return "ApiCallException{" +
                "message='" + message + '\'' +
                ", exceptionCode=" + exceptionCode +
                ", serverResponse='" + serverResponse + '\'' +
                ", api='" + api + '\'' +
                ", method=" + method +
                '}';
    }

}
