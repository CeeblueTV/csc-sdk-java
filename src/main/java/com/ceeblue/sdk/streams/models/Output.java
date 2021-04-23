package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output {
    private boolean passthrough;
    private Integer version;
    private Overlay overlay;
    private List<Track> tracks;


    public Output(boolean passthrough) {
        this.passthrough = passthrough;
    }

    public Output passthrough(boolean passthrough) {
        this.passthrough = passthrough;
        return this;
    }

    public Output version(Integer version) {
        this.version = version;
        return this;
    }

    public Output Overlay(Overlay overlay) {
        this.overlay = overlay;
        return this;
    }

    public Output tracks(List<Track> tracks) {
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
}
