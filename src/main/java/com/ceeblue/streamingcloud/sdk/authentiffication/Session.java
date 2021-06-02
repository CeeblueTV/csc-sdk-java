package com.ceeblue.streamingcloud.sdk.authentiffication;


public class Session {

    /**
     * endpoint of api server e.g shark.ceeblue.tv
     */
    String endpoint;

    /***
     * Auth token that server return's when authenticated
     */
    String token;

    public Session(String endpoint, String token) {
        this.endpoint = endpoint;
        this.token = token;
    }

    public Session() {
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Session setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Session setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return endpoint;
    }

}
