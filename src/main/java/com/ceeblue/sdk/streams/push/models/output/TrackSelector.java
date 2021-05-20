package com.ceeblue.sdk.streams.push.models.output;

import com.ceeblue.sdk.streams.input.models.CodecName;
import com.ceeblue.sdk.streams.input.models.tracks.TrackType;

public class TrackSelector {
    TrackType type;
    int idx;
    CodecName codecName;
    VideoResolutionSelector resolution;

    public TrackSelector() {
    }

    public TrackSelector(TrackType type) {
        this.type = type;
    }

    public TrackType getType() {
        return type;
    }

    public TrackSelector setType(TrackType type) {
        this.type = type;
        return this;
    }

    public int getIdx() {
        return idx;
    }

    public TrackSelector setIdx(int idx) {
        this.idx = idx;
        return this;
    }

    public CodecName getCodecName() {
        return codecName;
    }

    public TrackSelector setCodecName(CodecName codecName) {
        this.codecName = codecName;
        return this;
    }

    public VideoResolutionSelector getResolution() {
        return resolution;
    }

    public TrackSelector setResolution(VideoResolutionSelector resolution) {
        this.resolution = resolution;
        return this;
    }
}
