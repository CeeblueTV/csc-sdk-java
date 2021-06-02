package com.ceeblue.streamingcloud.sdk.streams.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class StingFormatter {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String getServerMessage(String response) {
        try {
            Map map = mapper.readValue(response, Map.class);

            return map.get("message").toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
