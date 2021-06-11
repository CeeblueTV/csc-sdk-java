package com.ceeblue.streamingcloud.sdk.streams.push.models.push;

import com.ceeblue.streamingcloud.sdk.streams.push.models.PushFormat;
import com.ceeblue.streamingcloud.sdk.streams.push.models.output.OutputParent;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class Push {

    /**
     * Push stream format [ RTMP | CMAF | UDPTS ]
     * Required
     */
    private PushFormat format;

    /**
     * Stream identifier
     * Required
     */
    private String streamId;

    /**
     * Push endpoint details
     * Required
     */
    private OutputParent output;

    /**
     * Starts pushing on stream state Ingestion
     */
    private Boolean autostart;

    public Push() {
    }

    public Push(PushFormat format, String streamId, OutputParent output) {
        this.format = format;
        this.streamId = streamId;
        this.output = output;
    }

    public Push(PushFormat format, String streamId, OutputParent output, Boolean autostart) {
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

    public Boolean isAutostart() {
        return autostart;
    }

    public Push setAutostart(Boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    @Override
    public String toString() {
        return "Push{" +
                (format != null ? "format=" + format : "") +
                (streamId != null ? ", streamId='" + streamId : "") +
                (output != null ? ", output=" + output : "") +
                (autostart != null ? ", autostart=" + autostart : "") +
                '}';
    }
}
