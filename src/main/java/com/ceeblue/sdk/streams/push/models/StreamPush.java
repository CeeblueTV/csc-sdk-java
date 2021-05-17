package com.ceeblue.sdk.streams.push.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamPush {
    PushFormat format;
    String streamId;
    @JsonProperty("output")
    String outputJson;
    boolean autostart;

    public StreamPush(PushFormat format, String streamId, String output) {
        this.format = format;
        this.streamId = streamId;
        this.outputJson = output;
    }

    public StreamPush(PushFormat format, String streamId, String outputJson, boolean autostart) {
        this.format = format;
        this.streamId = streamId;
        this.outputJson = outputJson;
        this.autostart = autostart;
    }

    public PushFormat getFormat() {
        return format;
    }

    public void setFormat(PushFormat format) {
        this.format = format;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getOutputJson() {
        return outputJson;
    }

    public void setOutputJson(String outputJson) {
        this.outputJson = outputJson;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }
}
