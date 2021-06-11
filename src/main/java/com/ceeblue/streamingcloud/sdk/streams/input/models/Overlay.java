package com.ceeblue.streamingcloud.sdk.streams.input.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Overlay {

    /**
     * The data URI image.
     */
    private final String data;

    /**
     * Horizontal offset of overlay image in pixels from left of video image. For negative value,
     * horizontal offset of overlay image in pixels from right of video image.
     */
    private final Integer offsetX;

    /**
     * Vertical offset of overlay image in pixels from top of video image. For negative value,
     * vertical offset of overlay image in pixels from bottom of video image.
     */
    private final Integer offsetY;

    public Overlay(String data, Integer offsetX, Integer offsetY) {
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

    @Override
    public String toString() {
        return "Overlay{" +
                (data != null ? "data=" + data : "") +
                (offsetX != null ? ", offsetX=" + offsetX : "") +
                (offsetY != null ? ", offsetY=" + offsetY : "") +
                '}';
    }
}
