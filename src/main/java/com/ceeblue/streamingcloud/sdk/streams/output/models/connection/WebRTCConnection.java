package com.ceeblue.streamingcloud.sdk.streams.output.models.connection;

public class WebRTCConnection extends Connection {

    /**
     * Streaming endpoint
     */
    String signallingUri;

    /**
     * STUN server endpoint
     */
    private String stun;

    /**
     * TURN endpoint
     */
    private String turn;

    public WebRTCConnection(String uri) {
        signallingUri = uri;
    }

    public String getStun() {
        return stun;
    }

    public WebRTCConnection setStun(String stun) {
        this.stun = stun;
        return this;
    }

    public String getTurn() {
        return turn;
    }

    public WebRTCConnection setTurn(String turn) {
        this.turn = turn;
        return this;
    }

    private String getSignallingUri() {
        return signallingUri;
    }

    private WebRTCConnection setSignallingUri(String signallingUri) {
        this.signallingUri = signallingUri;
        return this;
    }

}
