package com.ceeblue.streamingcloud.sdk.streams.output.models.output;

import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;
import com.ceeblue.streamingcloud.sdk.streams.output.models.Viewer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output {

    /**
     * Stream identifier
     * Required
     */
    private String streamId;

    /**
     * Output stream format
     * Required
     */
    private InputFormat format;

    /**
     * Number of concurrent sessions allowed
     */
    private Integer sessionLimit;

    /**
     * Viewer details
     */
    private Viewer viewer;

    /**
     * Invokes on status change
     */
    private String callbackUri;

    private Output() {
    }

    public Output(String streamId, InputFormat format) {
        this.streamId = streamId;
        this.format = format;
    }

    public String getStreamId() {
        return streamId;
    }

    public Output setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public InputFormat getFormat() {
        return format;
    }

    public Output setFormat(InputFormat format) {
        this.format = format;
        return this;
    }

    public Integer getSessionLimit() {
        return sessionLimit;
    }

    public Output setSessionLimit(Integer sessionLimit) {
        this.sessionLimit = sessionLimit;
        return this;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public Output setViewer(Viewer viewer) {
        this.viewer = viewer;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public Output setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    @Override
    public String toString() {
        return "Output{" +
                (streamId != null ? "streamId='" + streamId : "") +
                (format != null ? ", format=" + format : "") +
                (sessionLimit != null ? ", sessionLimit=" + sessionLimit : "") +
                (viewer != null ? ", viewer=" + viewer : "") +
                (callbackUri != null ? ", callbackUri='" + callbackUri : "") +
                "}";
    }
}
