package com.ceeblue.streamingcloud.sdk.streams.input.models.tracks;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = VideoTrack.class, name = "Video"),
        @JsonSubTypes.Type(value = AudioTrack.class, name = "Audio")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track {

    /**
     * Track type
     * Required
     */
    private TrackType type;

    /**
     * Encoder settings
     * Required
     */
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

    public Track setType(TrackType type) {
        this.type = type;
        return this;
    }

    public EncoderSettings getSettings() {
        return settings;
    }

    public Track setSettings(EncoderSettings settings) {
        this.settings = settings;
        return this;
    }

    @Override
    public String toString() {
        return "Track:{ " +
                (type != null ? "type=" + type : "") +
                (type != null ? ", settings=" + settings : "") +
                (this.getClass() == Track.class ? "}" : "");
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
