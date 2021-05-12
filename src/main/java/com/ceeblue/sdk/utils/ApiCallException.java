package com.ceeblue.sdk.utils;

public class ApiCallException extends RuntimeException {
    private final int exceptionCode;
    private final String serverResponse;

    public ApiCallException(String message, int exceptionCode, String serverResponse) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.serverResponse = serverResponse;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public String getServerResponse() {
        return serverResponse;
    }


    @Override
    public String toString() {
        return "ApiCallException{" +
                "exceptionCode=" + exceptionCode +
                ", serverResponse='" + serverResponse + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
