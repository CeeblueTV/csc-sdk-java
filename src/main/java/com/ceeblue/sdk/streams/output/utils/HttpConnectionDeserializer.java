package com.ceeblue.sdk.streams.output.utils;

import com.ceeblue.sdk.streams.output.models.connection.HttpConnection;
import com.ceeblue.sdk.streams.output.models.connection.WebRTCConnection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.ceeblue.sdk.streams.output.utils.CONSTANTS.*;

public class HttpConnectionDeserializer extends JsonDeserializer<HttpConnection> {

    @Override
    public HttpConnection deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        if (node.findValue(URI_JSON_NAME) != null) {
            return new HttpConnection().setUri(node.get(URI_JSON_NAME).asText());
        }

        return new WebRTCConnection(node.get(SIGNALLING_URI_JSON_NAME).asText())
                .setStun(node.get(STUN_JSON_NAME).asText())
                .setTurn(node.get(TURN_JSON_NAME).asText());
    }


}
