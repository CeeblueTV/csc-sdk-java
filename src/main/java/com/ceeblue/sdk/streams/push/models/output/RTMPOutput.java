package com.ceeblue.sdk.streams.push.models.output;

public class RTMPOutput {
    String server;
    String key;

    public RTMPOutput(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
