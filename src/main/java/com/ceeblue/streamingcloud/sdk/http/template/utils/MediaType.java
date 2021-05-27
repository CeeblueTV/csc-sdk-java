package com.ceeblue.streamingcloud.sdk.http.template.utils;

public enum MediaType {
    JSON("application/json"),
    IMAGE("image/jpg");

    private String type;

    MediaType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
