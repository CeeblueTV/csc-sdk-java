package com.ceeblue.streamingcloud.sdk.streams.input.models.inputs;

import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.InputFormat;
import com.ceeblue.streamingcloud.sdk.streams.input.models.InputStreamer;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Input {

    /**
     * Input stream format
     * Required
     */
    InputFormat format;

    /**
     * Streamer details
     */
    InputStreamer streamer;

    /**
     * Invokes on status change
     */
    String callbackUri;

    /**
     * Stream output settings
     */
    OutputSettings output = new OutputSettings(true);

    /**
     * Access mode [
     */
    Access access = Access.Public;

    /**
     * Access token. Will be generated automatically if empty. (Private access mode)
     */
    String accessToken;

    public InputFormat getFormat() {
        return format;
    }

    public void setFormat(InputFormat format) {
        this.format = format;
    }

    public InputStreamer getStreamer() {
        return streamer;
    }

    public void setStreamer(InputStreamer streamer) {
        this.streamer = streamer;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public OutputSettings getOutput() {
        return output;
    }

    public void setOutput(OutputSettings output) {
        this.output = output;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "format=" + format +
                ", streamer=" + streamer +
                ", callbackUri='" + callbackUri + '\'' +
                ", output=" + output +
                ", access=" + access +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

}
