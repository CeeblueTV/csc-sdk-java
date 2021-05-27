package com.ceeblue.streamingcloud.sdk.streams.input.models.tracks;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return type == track.type && Objects.equals(settings, track.settings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, settings);
    }
}
