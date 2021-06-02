package com.ceeblue.streamingcloud.sdk.streams.exceptions;

public class ClientException extends RuntimeException {

    private final String message;

    private RuntimeException exception;

    public ClientException(String message, RuntimeException exception) {
        this(message);
        this.exception = exception;
    }

    public ClientException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClientException:" +
                (message != null ? "Message='" + message : "") + '\n' +
                (exception != null ? "Exception=" + exception : "") + "\n" +
                '}';
    }

}

