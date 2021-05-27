package com.ceeblue.streamingcloud.sdk.streams.output.models.connection;

import com.ceeblue.streamingcloud.sdk.streams.output.utils.HttpConnectionDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = HttpConnectionDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpConnection {
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
}
