package com.ceeblue.streamingcloud.sdk.streams.input.models.inputs;

import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.InputStreamer;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Input {

    /**
     * Input stream format
     * Required
     */
    private InputFormat format;

    /**
     * Streamer details
     */
    private InputStreamer streamer;

    /**
     * Invokes on status change
     */
    private String callbackUri;

    /**
     * Stream output settings
     */
    private OutputSettings output = new OutputSettings();

    /**
     * Access mode [
     */
    private Access access = Access.Public;

    /**
     * Access token. Will be generated automatically if empty. (Private access mode)
     */
    private String accessToken;

    public Input(InputFormat format) {
        this.format = format;
    }

    public InputFormat getFormat() {
        return format;
    }

    public Input setFormat(InputFormat format) {
        this.format = format;
        return this;
    }

    public InputStreamer getStreamer() {
        return streamer;
    }

    public Input setStreamer(InputStreamer streamer) {
        this.streamer = streamer;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public Input setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    public OutputSettings getOutput() {
        return output;
    }

    public Input setOutput(OutputSettings output) {
        this.output = output;
        return this;
    }

    public Access getAccess() {
        return access;
    }

    public Input setAccess(Access access) {
        this.access = access;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Input setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public String toString() {
        return "Stream{ " +
                (format != null ? "format=" + format : "") +
                (format != null ? ", streamer=" + streamer : "") +
                (format != null ? ", callbackUri=" + callbackUri : "") +
                (format != null ? ", output=" + output : "") +
                (format != null ? ", access=" + access : "") +
                (format != null ? ", accessToken=" + accessToken : "") +
                " }";
    }

}
