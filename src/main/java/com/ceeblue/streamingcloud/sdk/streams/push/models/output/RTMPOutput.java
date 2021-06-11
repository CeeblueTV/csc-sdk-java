package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

public class RTMPOutput extends OutputParent {

    /**
     * RTMP server URL
     * Required
     */
    private String server;

    /**
     * RTMP stream key
     */
    private String key;

    public RTMPOutput() {
    }

    public RTMPOutput(String server, String key) {
        this.server = server;
        this.key = key;
    }

    public String getServer() {
        return server;
    }

    public RTMPOutput setServer(String server) {
        this.server = server;
        return this;
    }

    public String getKey() {
        return key;
    }

    public RTMPOutput setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String toString() {
        return "RTMPOutput{ " +
                (server != null ? "server=" + server : "") +
                (server != null ? ", key=" + key : "") +
                " }";
    }

}
