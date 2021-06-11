package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

public class CMAFOutput extends OutputParent {

    /**
     * Streaming endpoint
     * Required
     */
    private String endpoint;

    /**
     * Enable unified streaming platform support
     */
    private Boolean unifiedStreamingPlatform;

    private CMAFOutput() {
    }

    public CMAFOutput(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public CMAFOutput setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public boolean isUnifiedStreamingPlatform() {
        return unifiedStreamingPlatform;
    }

    public CMAFOutput setUnifiedStreamingPlatform(Boolean unifiedStreamingPlatform) {
        this.unifiedStreamingPlatform = unifiedStreamingPlatform;
        return this;
    }

    @Override
    public String toString() {
        return "CMAFOutput{" +
                (endpoint != null ? endpoint : "") +
                (endpoint != null ? ", unifiedStreamingPlatform=" + unifiedStreamingPlatform : "") +
                " } ";
    }
}
