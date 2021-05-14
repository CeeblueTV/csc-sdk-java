package com.ceeblue.sdk.authentiffication;

import org.springframework.stereotype.Component;

@Component
public class Session {
    String endpoint;
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
