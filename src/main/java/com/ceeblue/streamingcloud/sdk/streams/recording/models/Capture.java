package com.ceeblue.streamingcloud.sdk.streams.recording.models;

import com.ceeblue.streamingcloud.sdk.streams.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.push.models.output.TrackSelector;

/**
 * Stream capture settings
 */
public class Capture {

    /**
     * List of track selectors (capture all if empty)
     * Required
     */
    private TrackSelector trackSelector;

    /**
     * Capturing source [ Incoming | Outgoing ]
     * Required
     */
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
