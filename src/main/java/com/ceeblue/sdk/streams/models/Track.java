package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track {
    TrackType type;

    private EncoderSettings settings;

    public Track(TrackType type, EncoderSettings settings) {
        this.type = type;
        this.settings = settings;
    }

    public Track() {
    }

    public TrackType getType() {
        return type;
    }

    public void setType(TrackType type) {
        this.type = type;
    }

    public EncoderSettings getSettings() {
        return settings;
    }

    public void setSettings(EncoderSettings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "Track{" +
                "type=" + type +
                ", settings=" + settings +
                '}';
    }
}
