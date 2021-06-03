package com.ceeblue.streamingcloud.sdk.streams.output.models.connection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Input endpoint parameters for streaming
 */
@JsonDeserialize(using = HttpConnectionDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpConnection extends Connection {

    /**
     * Streaming endpoint
     */
    private String uri;

    public HttpConnection() {
    }

    public HttpConnection(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public HttpConnection setUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public String toString() {
        return "HttpConnection{" +
                "uri='" + uri + '\'' +
                '}';
    }

}
