package com.ceeblue.sdk.streams.push.models.output;

public class VideoResolutionSelector {
    private Integer width;
    private Integer height;
    private boolean approximate;

    public VideoResolutionSelector(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public VideoResolutionSelector setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public VideoResolutionSelector setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public boolean isApproximate() {
        return approximate;
    }

    public VideoResolutionSelector setApproximate(boolean approximate) {
        this.approximate = approximate;
        return this;
    }
}
