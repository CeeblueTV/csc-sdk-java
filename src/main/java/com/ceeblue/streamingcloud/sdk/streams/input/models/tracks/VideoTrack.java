package com.ceeblue.streamingcloud.sdk.streams.input.models.tracks;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTrack extends Track {

    /**
     * The width of the output (Min 2)
     */
    private Integer width;

    /**
     * The height of the output (Min 2)
     */
    private Integer height;

    /**
     * The frame rate of the output
     */
    private Integer framerate;

    private VideoTrack() {
    }

    public VideoTrack(EncoderSettings settings) {
        super(TrackType.Video, settings);
    }

    public Integer getWidth() {
        return width;
    }

    public VideoTrack setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public VideoTrack setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getFramerate() {
        return framerate;
    }

    public VideoTrack setFramerate(Integer framerate) {
        this.framerate = framerate;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", width=" + width +
                ", height=" + height +
                ", framerate=" + framerate +
                '}';
    }

}
