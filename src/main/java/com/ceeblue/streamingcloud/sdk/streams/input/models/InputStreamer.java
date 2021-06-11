package com.ceeblue.streamingcloud.sdk.streams.input.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputStreamer {

    /**
     * IP address
     */
    private String ip;

    /**
     * Geographical location
     */
    private GeoLocation geoLocation;

    public String getIp() {
        return ip;
    }

    public InputStreamer setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public GeoLocation getGeiLocation() {
        return geoLocation;
    }

    public InputStreamer setGeiLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
        return this;
    }
}
