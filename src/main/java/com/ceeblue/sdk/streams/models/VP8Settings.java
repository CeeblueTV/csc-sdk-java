package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VP8Settings extends EncoderSettings {

    private Integer keyMaxDist;

    public VP8Settings(CodecName codec, Integer bitrate, Integer keyMaxDist) {
        super(codec, bitrate);
        this.keyMaxDist = keyMaxDist;
    }

    public Integer getKeyMaxDist() {
        return keyMaxDist;
    }

    public void setKeyMaxDist(Integer keyMaxDist) {
        this.keyMaxDist = keyMaxDist;
    }

    @Override
    public String toString() {
        return "VP8Settings{" +
                "codec=" + codec +
                ", bitrate=" + bitrate +
                ", keyMaxDist=" + keyMaxDist +
                '}';
    }
}
