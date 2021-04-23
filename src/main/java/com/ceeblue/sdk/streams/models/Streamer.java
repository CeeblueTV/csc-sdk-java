package com.ceeblue.sdk.streams.models;

class Streamer {
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

