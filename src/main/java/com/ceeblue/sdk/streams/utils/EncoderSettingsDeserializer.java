//package com.ceeblue.sdk.streams.utils;
//
//import com.ceeblue.sdk.streams.models.CodecName;
//import com.ceeblue.sdk.streams.models.EncoderSettings;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//
//import java.io.IOException;
//
//public class EncoderSettingsDeserializer extends JsonDeserializer<EncoderSettings> {
//    @Override
//    public EncoderSettings deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
//        JsonNode node = parser.getCodec().readTree(parser);
//        CodecName codec = (CodecName) node.get("codec").asText();
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//        int i= 3,a = 5, x=7;
//    }
//}
