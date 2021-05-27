package com.ceeblue.streamingcloud.sdk.streams.input.models.encoder;

import com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName;
import com.ceeblue.streamingcloud.sdk.streams.input.models.SpeedPreset;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class H264Settings extends EncoderSettings {
    /**
     * Preset name for speed/quality tradeoff options
     */
    private SpeedPreset speedPreset;
    /**
     * Maximal distance between two key-frames (Min 2)
     */
    private Integer keyIntMax;

    public H264Settings() {
    }

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

    @Override
    public String toString() {
        return "H264Settings{" +
                "codec=" + codec +
                ", bitrate=" + bitrate +
                ", speedPreset=" + speedPreset +
                ", keyIntMax=" + keyIntMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        H264Settings that = (H264Settings) o;
        return speedPreset == that.speedPreset && Objects.equals(keyIntMax, that.keyIntMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speedPreset, keyIntMax);
    }
}
