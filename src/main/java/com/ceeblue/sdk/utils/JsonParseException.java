package com.ceeblue.sdk.utils;

public class JsonParseException extends RuntimeException {
    private final Throwable originalError;
    String message;

    public JsonParseException(String message, Throwable originalError) {
        this.message = message;
        this.originalError = originalError;
    }

    public Throwable getOriginalError() {
        return originalError;
    }

    @Override
    public String toString() {
        return "JsonParseException{" +
                "originalError=" + originalError +
                ", message='" + message + '\'' +
                '}';
    }
}
