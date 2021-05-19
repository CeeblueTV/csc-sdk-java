package com.ceeblue.sdk.streams.output.models.connection;

import com.ceeblue.sdk.streams.input.utils.EncoderSettingsDeserializer;
import com.ceeblue.sdk.streams.output.utils.HttpConnectionDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import static com.ceeblue.sdk.streams.output.utils.CONSTANTS.SIGNALLING_URI_JSON_NAME;
import static com.ceeblue.sdk.streams.output.utils.CONSTANTS.URI_JSON_NAME;

@JsonDeserialize(using = HttpConnectionDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpConnection {
    @JsonAlias({URI_JSON_NAME, SIGNALLING_URI_JSON_NAME})
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
