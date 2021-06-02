package com.ceeblue.streamingcloud.sdk.streams.input.models;

import com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.Track;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputSettings {

    /**
     * If true, enables a signal to "pass through" unaltered. If false, enables multiprofile transcoding.
     */
    private boolean passthrough;

    /**
     * Output configuration version [ 1 | 2 ] (Version 1 by default)
     */
    private Integer version;

    /**
     * Overlays an picture onto a video track
     */
    private Overlay overlay;

    /**
     * List of video (and audio tracks for the version 2) transcoding tracks
     * required in version 2
     */
    private List <Track> tracks;

    public OutputSettings() {
    }

    public OutputSettings(boolean passthrough) {
        this.passthrough = passthrough;
    }

    public OutputSettings passthrough(boolean passthrough) {
        this.passthrough = passthrough;
        return this;
    }

    public OutputSettings version(Integer version) {
        this.version = version;
        return this;
    }

    public OutputSettings Overlay(Overlay overlay) {
        this.overlay = overlay;
        return this;
    }

    public OutputSettings tracks(List <Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public boolean isPassthrough() {
        return passthrough;
    }

    public OutputSettings setPassthrough(boolean passthrough) {
        this.passthrough = passthrough;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public OutputSettings setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Overlay getOverlay() {
        return overlay;
    }

    public OutputSettings setOverlay(Overlay overlay) {
        this.overlay = overlay;
        return this;
    }

    public List <Track> getTracks() {
        return tracks;
    }

    public OutputSettings setTracks(List <Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    @Override
    public String toString() {
        return "Output{" +
                "passthrough=" + passthrough +
                ", version=" + version +
                ", overlay=" + overlay +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputSettings output = (OutputSettings) o;
        return passthrough == output.passthrough && version.equals(output.version) && Objects.equals(overlay, output.overlay) && tracks.equals(output.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passthrough, version, overlay, tracks);
    }

}
