package com.ceeblue.streamingcloud.sdk.streams.output.utils;

import com.ceeblue.streamingcloud.sdk.streams.output.models.connection.HttpConnection;
import com.ceeblue.streamingcloud.sdk.streams.output.models.connection.WebRTCConnection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class HttpConnectionDeserializer extends JsonDeserializer<HttpConnection> {

    @Override
    public HttpConnection deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        if (node.has(Constants.URI_JSON_NAME)) {
            return new HttpConnection().setUri(node.get(Constants.URI_JSON_NAME).asText());
        }

        return new WebRTCConnection(node.get(Constants.SIGNALLING_URI_JSON_NAME).asText())
                .setStun(getString(Constants.STUN_JSON_NAME, node))
                .setTurn(getString(Constants.TURN_JSON_NAME, node));
    }

    String getString(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asText() : null;
    }

}
