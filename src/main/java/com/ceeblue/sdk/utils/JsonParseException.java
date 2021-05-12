package com.ceeblue.sdk.utils;

public class JsonParseException extends RuntimeException {
    private final Throwable originalError;

    public JsonParseException(String message, Throwable originalError) {
        super(message);
        this.originalError = originalError;
    }

    public Throwable getOriginalError() {
        return originalError;
    }
}
