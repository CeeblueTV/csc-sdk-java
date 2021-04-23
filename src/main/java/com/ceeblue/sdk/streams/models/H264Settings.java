package com.ceeblue.sdk.streams.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class H264Settings extends EncoderSettings{
    private SpeedPreset speedPreset;
    private Integer keyIntMax;

    public H264Settings(CodecName codec, Integer bitrate, SpeedPreset speedPreset, Integer keyIntMax) {
        super(codec, bitrate);
        this.speedPreset = speedPreset;
        this.keyIntMax = keyIntMax;
    }

    public SpeedPreset getSpeedPreset() {
        return speedPreset;
    }

    public void setSpeedPreset(SpeedPreset speedPreset) {
        this.speedPreset = speedPreset;
    }

    public Integer getKeyIntMax() {
        return keyIntMax;
    }

    public void setKeyIntMax(Integer keyIntMax) {
        this.keyIntMax = keyIntMax;
    }
}
