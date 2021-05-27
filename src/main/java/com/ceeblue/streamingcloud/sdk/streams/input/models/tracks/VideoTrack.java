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

    public VideoTrack(TrackType type, EncoderSettings settings) {
        super(type, settings);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFramerate() {
        return framerate;
    }

    public void setFramerate(Integer framerate) {
        this.framerate = framerate;
    }
}
