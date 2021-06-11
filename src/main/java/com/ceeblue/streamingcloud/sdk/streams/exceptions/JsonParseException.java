package com.ceeblue.streamingcloud.sdk.streams.exceptions;

public class JsonParseException extends RuntimeException {

    private final String message;

    private Throwable originalError;

    public JsonParseException(String message, Throwable originalError) {
        this.message = message;
        this.originalError = originalError;
    }

    public JsonParseException(String message) {
        this.message = message;
    }

    public Throwable getOriginalError() {
        return originalError;
    }

    @Override
    public String toString() {
        return "JsonParseException{" +
                (originalError != null ? "originalError=" + originalError : "") +
                (message != null ? ", message='" + message : "") + '\'' +
                '}';
    }
}
