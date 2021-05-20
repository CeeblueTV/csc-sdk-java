package com.ceeblue.sdk.streams.input.models.tracks;

import com.ceeblue.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTrack extends Track {
    private Integer width;
    private Integer height;
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
