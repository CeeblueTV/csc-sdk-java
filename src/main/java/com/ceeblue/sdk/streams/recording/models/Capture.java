package com.ceeblue.sdk.streams.recording.models;

import com.ceeblue.sdk.streams.push.models.output.TrackSelector;

public class Capture {
    private TrackSelector trackSelector;
    private Source source;

    public Capture(Source source, TrackSelector trackSelector) {
        this.source = source;
        this.trackSelector = trackSelector;
    }

    public Capture() {
    }

    public Source getSource() {
        return source;
    }

    public Capture setSource(Source source) {
        this.source = source;
        return this;
    }

    public TrackSelector getTrackSelector() {
        return trackSelector;
    }

    public Capture setTrackSelector(TrackSelector trackSelector) {
        this.trackSelector = trackSelector;
        return this;
    }
}
