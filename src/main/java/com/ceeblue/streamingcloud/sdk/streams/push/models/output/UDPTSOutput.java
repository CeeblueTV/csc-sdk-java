package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

import java.util.List;

public class UDPTSOutput extends OutputParent {

    /**
     * Streaming endpoint IP adress
     */
    private String ipAddress;

    /**
     * Streaming endpoint port
     */
    private Integer port;

    /**
     * List of track selectors (capture all if empty)
     */
    private List<TrackSelector> tracks;

    private UDPTSOutput() {
    }

    public UDPTSOutput(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public UDPTSOutput setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public int getPort() {
        return port;
    }

    public UDPTSOutput setPort(int port) {
        this.port = port;
        return this;
    }

    public List<TrackSelector> getTracks() {
        return tracks;
    }

    public UDPTSOutput setTracks(List<TrackSelector> tracks) {
        this.tracks = tracks;
        return this;
    }

    @Override
    public String toString() {
        return "UDPTSOutput{" +
                (ipAddress != null ? "ipAddress='" + ipAddress + '\'' : "") +
                (port != null ? ", port=" + port : "") +
                (tracks != null ? ", tracks=" + tracks : "") +
                "} ";
    }
}
