package com.ceeblue.sdk.streams.utils;

import com.ceeblue.sdk.streams.input.models.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EncoderSettingsDeserializer extends JsonDeserializer<EncoderSettings> {
    @Override
    public EncoderSettings deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);


        final CodecName codec = CodecName.valueOf(node.get("codec").asText());
        final Integer bitrate = node.get("bitrate").asInt();

        EncoderSettings settings = new EncoderSettings(codec, bitrate);

        if (codec == CodecName.H264) {
            final SpeedPreset speedPreset = SpeedPreset.valueOf(node.get("speedPreset").asText());
            final Integer keyIntMax = node.get("keyIntMax").asInt();

            settings = new H264Settings(codec, bitrate, speedPreset, keyIntMax);
        } else if (codec == CodecName.VP8) {
            final Integer keyIntMax = node.get("keyMaxDist").asInt();

            settings = new VP8Settings(codec, bitrate, keyIntMax);
        }

        return settings;
    }


}
