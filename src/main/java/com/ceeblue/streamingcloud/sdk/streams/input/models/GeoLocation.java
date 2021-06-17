package com.ceeblue.streamingcloud.sdk.streams.input.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoLocation {

    /**
     * Latitude coordinate in decimal degree format
     * Required
     */
    private Double latitude;

    /**
     * Longitude coordinate in decimal degree format
     * Required
     */
    private Double longitude;

    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                (latitude != null ? "latitude=" + latitude : "") +
                (longitude != null ? ", longitude=" + longitude : "") +
                '}';
    }
}
