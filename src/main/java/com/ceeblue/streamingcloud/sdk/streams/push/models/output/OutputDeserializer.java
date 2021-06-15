package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class OutputDeserializer extends JsonDeserializer<OutputParent> {

    @Override
    public OutputParent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        if (node.has("server")) {
            return new RTMPOutput()
                    .setServer(getString("server", node))
                    .setKey(getString("key", node));
        }

        if (node.has("endpoint")) {
            return new CMAFOutput(getString("endpoint", node))
                    .setUnifiedStreamingPlatform(getBoolean("unifiedStreamingPlatform", node));
        }

        if (node.has("ipAddress")) {
            return new UDPTSOutput(getString("ipAddress", node), getInteger("port", node));
        }

        throw new JsonParseException("Couldn't find suitable representation of output");
    }

    String getString(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asText() : null;
    }

    Integer getInteger(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asInt() : null;
    }

    Boolean getBoolean(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asBoolean() : null;
    }
}
