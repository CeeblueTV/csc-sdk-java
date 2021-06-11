package com.ceeblue.streamingcloud.sdk.authentiffication;

public class Session {

    private String token;

    private String endpoint;

    public Session(String endpoint, String token) {
        this.endpoint = endpoint;
        this.token = token;
    }

    public Session() {
    }

    /**
     * Endpoint of API cloud e.g shark.ceeblue.tv
     */
    public String getEndpoint() {
        return endpoint;
    }

    public Session setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * Auth token that cloud return's when authenticated
     */
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
