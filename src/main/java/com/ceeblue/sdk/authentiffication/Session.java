package com.ceeblue.sdk.authentiffication;

import org.springframework.stereotype.Component;

@Component
public class Session {
    String endpoint = "https://shark.ceeblue.tv/api";
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

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
