package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

public class RTMPOutput extends OutputParent {

    String server;

    String key;

    public RTMPOutput(String server) {
        this.server = server;
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

}
