package com.ceeblue.streamingcloud.sdk.streams.input.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Overlay overlay = (Overlay) o;
        return Objects.equals(data, overlay.data) && Objects.equals(offsetX, overlay.offsetX) && Objects.equals(offsetY, overlay.offsetY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, offsetX, offsetY);
    }
}
