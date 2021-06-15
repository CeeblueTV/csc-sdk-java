package com.ceeblue.streamingcloud.sdk.streams.output.models.connection;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class HttpConnectionDeserializer extends JsonDeserializer<Connection> {

    private static final String URI_JSON_NAME = "uri";

    private static final String SIGNALLING_URI_JSON_NAME = "signallingUri";

    private static final String STUN_JSON_NAME = "stun";

    private static final String TURN_JSON_NAME = "turn";

    @Override
    public Connection deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        if (node.has(URI_JSON_NAME)) {
            return new HttpConnection().setUri(node.get(URI_JSON_NAME).asText());
        }
        if (node.has(SIGNALLING_URI_JSON_NAME)) {
            return new WebRTCConnection(node.get(SIGNALLING_URI_JSON_NAME).asText())
                    .setStun(getString(STUN_JSON_NAME, node))
                    .setTurn(getString(TURN_JSON_NAME, node));
        }

        throw new JsonParseException("No suitable http connection type weren't found");
    }

    String getString(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asText() : null;
    }

}
