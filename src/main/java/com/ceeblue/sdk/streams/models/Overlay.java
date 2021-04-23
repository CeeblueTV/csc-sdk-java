package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Overlay {
    String data;
    Double offsetX;
    Double offsetY;

    public Overlay(String data, Double offsetX, Double offsetY) {
        this.data = data;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}
