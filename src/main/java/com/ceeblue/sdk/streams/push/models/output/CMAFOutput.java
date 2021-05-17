package com.ceeblue.sdk.streams.push.models.output;

public class CMAFOutput {
    String endpoint;
    boolean unifiedStreamingPlatform;

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

    public void setUnifiedStreamingPlatform(boolean unifiedStreamingPlatform) {
        this.unifiedStreamingPlatform = unifiedStreamingPlatform;
    }
}
