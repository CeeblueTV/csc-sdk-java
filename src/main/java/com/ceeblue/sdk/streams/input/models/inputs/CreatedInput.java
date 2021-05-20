package com.ceeblue.sdk.streams.input.models.inputs;

import com.ceeblue.sdk.streams.input.models.*;
import com.ceeblue.sdk.streams.output.models.connection.HttpConnection;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedInput {
    private String id;
    private InputFormat format;
    private Streamer streamer;
    private Status status;
    private Access access;
    private String accessToken;
    private String callbackUri;
    private List<String> statusMessages;
    private HttpConnection connection;
    private Parameters parameters;

    public String getId() {
        return id;
    }

    public CreatedInput setId(String id) {
        this.id = id;
        return this;
    }

    public InputFormat getFormat() {
        return format;
    }

    public CreatedInput setFormat(InputFormat format) {
        this.format = format;
        return this;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public CreatedInput setStreamer(Streamer streamer) {
        this.streamer = streamer;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public CreatedInput setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Access getAccess() {
        return access;
    }

    public CreatedInput setAccess(Access access) {
        this.access = access;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public CreatedInput setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public CreatedInput setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    public List<String> getStatusMessages() {
        return statusMessages;
    }

    public CreatedInput setStatusMessages(List<String> statusMessages) {
        this.statusMessages = statusMessages;
        return this;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public CreatedInput setParameters(Parameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public HttpConnection getConnection() {
        return connection;
    }

    public CreatedInput setConnection(HttpConnection connection) {
        this.connection = connection;
        return this;
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
                ", connection=" + (parameters != null ? parameters.toString() : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedInput that = (CreatedInput) o;
        return Objects.equals(id, that.id) && format == that.format && Objects.equals(streamer, that.streamer) && status == that.status && access == that.access && Objects.equals(accessToken, that.accessToken) && Objects.equals(callbackUri, that.callbackUri) && Objects.equals(statusMessages, that.statusMessages) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, streamer, status, access, accessToken, callbackUri, statusMessages, parameters);
    }
}
