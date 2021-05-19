package com.ceeblue.sdk.streams.output.models.connection;

public class WebRTCConnection extends HttpConnection {
    private String stun;
    private String turn;

    public WebRTCConnection(String uri) {
        super(uri);
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
}
