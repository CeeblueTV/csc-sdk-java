package com.ceeblue.sdk.streams.push.models.push;

import com.ceeblue.sdk.streams.push.models.PushFormat;
import com.ceeblue.sdk.streams.push.models.output.OutputParent;

public class Push {
    PushFormat format;
    String streamId;
    OutputParent output;
    boolean autostart;

    public Push(PushFormat format, String streamId, OutputParent output) {
        this.format = format;
        this.streamId = streamId;
        this.output = output;
    }

    public Push(PushFormat format, String streamId, OutputParent output, boolean autostart) {
        this.format = format;
        this.streamId = streamId;
        this.output = output;
        this.autostart = autostart;
    }


    public PushFormat getFormat() {
        return format;
    }

    public Push setFormat(PushFormat format) {
        this.format = format;
        return this;
    }

    public String getStreamId() {
        return streamId;
    }

    public Push setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public OutputParent getOutput() {
        return output;
    }

    public Push setOutput(OutputParent output) {
        this.output = output;
        return this;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public Push setAutostart(boolean autostart) {
        this.autostart = autostart;
        return this;
    }
}
