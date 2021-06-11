package com.ceeblue.streamingcloud.sdk.streams.input.models;

/**
 * Streamer details
 */
public class Streamer {

    /**
     * IP address
     */
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Streamer{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }
}

