package com.ceeblue.sdk.streams.push.models.output;

import java.util.List;

public class UDPTSOutput {
    String ipAddress;
    int port;
    List<TrackSelector> tracks;

    public UDPTSOutput(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<TrackSelector> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackSelector> tracks) {
        this.tracks = tracks;
    }
}
