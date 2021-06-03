package com.ceeblue.streamingcloud.sdk.streams.input.models.inputs;

import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.Parameters;
import com.ceeblue.streamingcloud.sdk.streams.input.models.Status;
import com.ceeblue.streamingcloud.sdk.streams.input.models.Streamer;
import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;
import com.ceeblue.streamingcloud.sdk.streams.output.models.connection.Connection;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputEndpoint {

    /**
     * Stream identifier
     */
    private String id;

    /**
     * Input stream format
     */
    private InputFormat format;

    /**
     * Streamer details
     */
    private Streamer streamer;

    /**
     * Stream  status
     */
    private Status status;

    /**
     * Stream access status
     */
    private Access access;

    /**
     * Access token. Will be generated automatically if empty.
     */
    private String accessToken;

    /**
     * Invokes on status change.
     */
    private String callbackUri;

    /**
     * List of messages
     */
    private List <String> statusMessages;

    /**
     * Input endpoint parameters for streaming
     */
    private Connection connection;

    /**
     * Input stream parameters
     */
    private Parameters parameters;

    public String getId() {
        return id;
    }

    public InputEndpoint setId(String id) {
        this.id = id;
        return this;
    }

    public InputFormat getFormat() {
        return format;
    }

    public InputEndpoint setFormat(InputFormat format) {
        this.format = format;
        return this;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public InputEndpoint setStreamer(Streamer streamer) {
        this.streamer = streamer;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public InputEndpoint setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Access getAccess() {
        return access;
    }

    public InputEndpoint setAccess(Access access) {
        this.access = access;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public InputEndpoint setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public InputEndpoint setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    public List <String> getStatusMessages() {
        return statusMessages;
    }

    public InputEndpoint setStatusMessages(List <String> statusMessages) {
        this.statusMessages = statusMessages;
        return this;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public InputEndpoint setParameters(Parameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public Connection getConnection() {
        return connection;
    }

    public InputEndpoint setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    @Override
    public String toString() {
        return "CreatedStream{" +
                (id != null ? "id=" + id : "") +
                (format != null ? ", format=" + format : "") +
                (streamer != null ? ", streamer=" + streamer : "") +
                (status != null ? ", status=" + status : "") +
                (access != null ? ", access=" + access : "") +
                (accessToken != null ? ", accessToken='" + accessToken : "") +
                (parameters != null ? ", parameters=" + parameters : "") +
                (callbackUri != null ? ", callbackUri='" + callbackUri : "") +
                (statusMessages != null ? ", statusMessages=" + statusMessages : "") +
                (connection != null ? ", connection=" + connection : "") +
                (parameters != null ? ", parameters=" + parameters : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputEndpoint that = (InputEndpoint) o;
        return Objects.equals(id, that.id) && format == that.format && Objects.equals(streamer, that.streamer) && status == that.status && access == that.access && Objects.equals(accessToken, that.accessToken) && Objects.equals(callbackUri, that.callbackUri) && Objects.equals(statusMessages, that.statusMessages) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, streamer, status, access, accessToken, callbackUri, statusMessages, parameters);
    }

}
