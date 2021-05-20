package com.ceeblue.sdk.streams.output.models.output;

import com.ceeblue.sdk.streams.output.models.Viewer;
import com.ceeblue.sdk.streams.output.models.connection.HttpConnection;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.ceeblue.sdk.streams.output.utils.Constants.CONNECTION_JSON_NAME;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedOutput {

    private List<String> sessions;
    private String id;
    @JsonProperty(CONNECTION_JSON_NAME)
    private HttpConnection connection;
    private Viewer viewer;
    private String callbackUri;
    private Integer sessionLimit;

    public CreatedOutput() {
    }

    public HttpConnection getConnection() {
        return connection;
    }

    public CreatedOutput setConnection(HttpConnection connection) {
        this.connection = connection;
        return this;
    }

    public String getId() {
        return id;
    }

    public CreatedOutput setId(String id) {
        this.id = id;
        return this;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public CreatedOutput setViewer(Viewer viewer) {
        this.viewer = viewer;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public CreatedOutput setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    public Integer getSessionLimit() {
        return sessionLimit;
    }

    public CreatedOutput setSessionLimit(Integer sessionLimit) {
        this.sessionLimit = sessionLimit;
        return this;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public CreatedOutput setSessions(List<String> sessions) {
        this.sessions = sessions;
        return this;
    }
}
