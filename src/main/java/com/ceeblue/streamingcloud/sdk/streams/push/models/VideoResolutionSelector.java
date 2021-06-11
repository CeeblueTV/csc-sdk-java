package com.ceeblue.streamingcloud.sdk.streams.push.models;

public class VideoResolutionSelector {

    /**
     * Picture width
     * Required
     */
    private Integer width;

    /**
     * Picture height
     * Required
     */
    private Integer height;

    /**
     * Choose the track closest to the given pixel surface area
     */
    private Boolean approximate;

    private VideoResolutionSelector() {
    }

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

    public VideoResolutionSelector setApproximate(Boolean approximate) {
        this.approximate = approximate;
        return this;
    }

    @Override
    public String toString() {
        return "VideoResolutionSelector{" +
                (width != null ? " width=" + width : "") +
                (height != null ? ", height=" + height : "") +
                (approximate != null ? ", approximate=" + approximate : "") +
                '}';
    }
}
