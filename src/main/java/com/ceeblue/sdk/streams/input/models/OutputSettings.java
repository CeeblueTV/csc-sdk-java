package com.ceeblue.sdk.streams.input.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputSettings {
    private boolean passthrough;
    private Integer version;
    private Overlay overlay;
    private List<Track> tracks;

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

    public OutputSettings tracks(List<Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public boolean isPassthrough() {
        return passthrough;
    }

    public void setPassthrough(boolean passthrough) {
        this.passthrough = passthrough;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Overlay getOverlay() {
        return overlay;
    }

    public void setOverlay(Overlay overlay) {
        this.overlay = overlay;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
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
