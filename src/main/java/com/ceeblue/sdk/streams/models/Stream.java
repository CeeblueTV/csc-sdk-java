package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stream {
    InputFormat format;
    InputStreamer streamer;
    String callbackUri;
    Output output = new Output(true);
    Access access = Access.Public;
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

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
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
