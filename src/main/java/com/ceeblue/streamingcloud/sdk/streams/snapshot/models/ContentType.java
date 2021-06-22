package com.ceeblue.streamingcloud.sdk.streams.snapshot.models;

public enum ContentType {
    PNG("image/png", "png"),
    JPEG("image/jpg", "jpg");

    public final String contentType;

    public final String format;

    ContentType(String contentType, String format) {
        this.contentType = contentType;
        this.format = format;
    }
}
