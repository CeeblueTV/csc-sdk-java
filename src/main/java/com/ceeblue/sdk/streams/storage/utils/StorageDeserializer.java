package com.ceeblue.sdk.streams.storage.utils;

import com.ceeblue.sdk.streams.storage.models.StorageType;
import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3Compatible;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.ceeblue.sdk.streams.storage.utils.Constants.*;

public class StorageDeserializer extends JsonDeserializer<AmazonS3> {


    @Override
    public AmazonS3 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        String name = node.has(ID) ? node.get(ID).asText() : node.get(NAME).asText();

        if (node.findValue(StorageType.AmazonS3.name()) != null) {
            return new AmazonS3()
                    .setName(name)
                    .setPath(getString(PATH, node))
                    .setAccessKeyId(getString(ACCESS_KEY_ID, node))
                    .setSecretAccessKey(getString(SECRET_ACCESS_KEY, node))
                    .setBucket(getString(BUCKET, node));
        }

        return new AmazonS3Compatible()
                .setEndpoint(getString(ENDPOINT, node))
                .setName(name)
                .setPath(getString(PATH, node))
                .setAccessKeyId(getString(ACCESS_KEY_ID, node))
                .setSecretAccessKey(getString(SECRET_ACCESS_KEY, node))
                .setBucket(getString(BUCKET, node));
    }

    String getString(String jsonName, JsonNode node) {
        return node.has(jsonName) ? node.get(jsonName).asText() : null;
    }
}
