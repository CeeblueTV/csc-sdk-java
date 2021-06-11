package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

import com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName;
import com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.TrackType;
import com.ceeblue.streamingcloud.sdk.streams.push.models.VideoResolutionSelector;

public class TrackSelector {

    /**
     * Track type [ Video | Audio ]
     * Required
     */
    private TrackType type;

    /**
     * Track identifier
     */
    private Integer idx;

    /**
     * Codec type [ H264 | VP8 | VP9 | AAC | MP3| Opus ]
     */
    private CodecName codecName;

    /**
     * Video track selector by resolution
     */
    private VideoResolutionSelector resolution;

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

    @Override
    public String toString() {
        return "TrackSelector{ " +
                (type != null ? "type=" + type : "") +
                (idx != null ? ", idx=" + idx : "") +
                (codecName != null ? ", codecName=" + codecName : "") +
                (resolution != null ? ", resolution=" + resolution : "") +
                "}";
    }
}
