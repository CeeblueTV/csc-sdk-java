package com.ceeblue.streamingcloud.sdk.streams.output.models;

import com.ceeblue.streamingcloud.sdk.streams.input.models.GeoLocation;

/**
 * Viewer details
 */
public class Viewer {

    /**
     * Stream access token
     */
    private String accessToken;

    /**
     * IP address
     */
    private String ipAddress;

    /**
     * Geographical location
     */
    private GeoLocation geoLocation;

    public Viewer() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Viewer setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Viewer setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public Viewer setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
        return this;
    }

    @Override
    public String toString() {
        return "Viewer{" +
                (accessToken != null ? "accessToken= '" + accessToken + '\'' : "") +
                (ipAddress != null ? " ipAddress= " + ipAddress : "") +
                (geoLocation != null ? " geoLocation= " + geoLocation : "") +
                '}';
    }

}
