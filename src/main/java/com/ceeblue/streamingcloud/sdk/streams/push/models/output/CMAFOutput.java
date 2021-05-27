package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

public class CMAFOutput extends OutputParent {
    private String endpoint;
    private Boolean unifiedStreamingPlatform;

    public CMAFOutput(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isUnifiedStreamingPlatform() {
        return unifiedStreamingPlatform;
    }

    public CMAFOutput setUnifiedStreamingPlatform(Boolean unifiedStreamingPlatform) {
        this.unifiedStreamingPlatform = unifiedStreamingPlatform;
        return this;
    }
}
