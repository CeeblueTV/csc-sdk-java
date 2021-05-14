package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedStream {
    private String id;
    private InputFormat format;
    private Streamer streamer;
    private Status status;
    private Access access;
    private String accessToken;
    private String callbackUri;
    private List<String> statusMessages;
    @JsonProperty("connection")
    private Parameters connection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InputFormat getFormat() {
        return format;
    }

    public void setFormat(InputFormat format) {
        this.format = format;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public void setStreamer(Streamer streamer) {
        this.streamer = streamer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public List<String> getStatusMessages() {
        return statusMessages;
    }

    public void setStatusMessages(List<String> statusMessages) {
        this.statusMessages = statusMessages;
    }

    public Parameters getConnection() {
        return connection;
    }

    public void setConnection(Parameters connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "CreatedStream{" +
                "id=" + (id != null ? id : "") +
                ", format=" + (format != null ? format.toString() : "") +
                ", streamer=" + (streamer != null ? streamer.toString() : "") +
                ", status=" + (status != null ? status.toString() : "") +
                ", access=" + (access != null ? access.toString() : "") +
                ", accessToken='" + (accessToken != null ? accessToken : "") +
                ", callbackUri='" + (callbackUri != null ? callbackUri : "") +
                ", statusMessages=" + (statusMessages != null ? statusMessages.toString() : "") +
                ", connection=" + (connection != null ? connection.toString() : "") +
                '}';
    }


}
