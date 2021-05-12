package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VP8Settings extends EncoderSettings {

    @JsonProperty("keyMaxDist")
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
}
