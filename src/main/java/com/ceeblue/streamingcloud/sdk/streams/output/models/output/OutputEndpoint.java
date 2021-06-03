package com.ceeblue.streamingcloud.sdk.streams.output.models.output;

import com.ceeblue.streamingcloud.sdk.streams.output.models.Viewer;
import com.ceeblue.streamingcloud.sdk.streams.output.models.connection.Connection;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputEndpoint {

    /**
     * Output active sessions
     */
    private List <String> sessions;

    /**
     * Stream identifier
     */
    private String id;

    /**
     * Output endpoint parameters for viewer
     */
    private Connection connection;

    /**
     * Viewer details
     */
    private Viewer viewer;

    /**
     * Invokes on status change
     */
    private String callbackUri;

    /**
     * Number of concurrent sessions allowed
     */
    private Integer sessionLimit;

    public OutputEndpoint() {
    }

    public Connection getConnection() {
        return connection;
    }

    public OutputEndpoint setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public String getId() {
        return id;
    }

    public OutputEndpoint setId(String id) {
        this.id = id;
        return this;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public OutputEndpoint setViewer(Viewer viewer) {
        this.viewer = viewer;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public OutputEndpoint setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    public Integer getSessionLimit() {
        return sessionLimit;
    }

    public OutputEndpoint setSessionLimit(Integer sessionLimit) {
        this.sessionLimit = sessionLimit;
        return this;
    }

    public List <String> getSessions() {
        return sessions;
    }

    public OutputEndpoint setSessions(List <String> sessions) {
        this.sessions = sessions;
        return this;
    }

    @Override
    public String toString() {
        return "OutputEndpoint{" +
                (sessions != null ? "sessions=" + sessions : "") +
                (id != null ? " id='" + id + '\'' : "") +
                (connection != null ? " connection=" + connection : "") +
                (viewer != null ? " viewer=" + viewer : "") +
                (callbackUri != null ? " callbackUri='" + callbackUri + '\'' : "") +
                (sessionLimit != null ? " sessionLimit=" + sessionLimit : "") +
                '}';
    }

}
