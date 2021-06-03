package com.ceeblue.streamingcloud.sdk.streams.input.models.encoder;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName.H264;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class H264Settings extends EncoderSettings {

    /**
     * Preset name for speed/quality tradeoff options
     * Required
     */
    private SpeedPreset speedPreset;

    /**
     * Maximal distance between two key-frames (Min 2)
     * Required
     */
    private Integer keyIntMax;

    public H264Settings() {
        codec = H264;
    }

    public H264Settings(Integer bitrate, SpeedPreset speedPreset, Integer keyIntMax) {
        super(H264, bitrate);
        this.speedPreset = speedPreset;
        this.keyIntMax = keyIntMax;
    }

    public SpeedPreset getSpeedPreset() {
        return speedPreset;
    }

    public H264Settings setSpeedPreset(SpeedPreset speedPreset) {
        this.speedPreset = speedPreset;
        return this;
    }

    public Integer getKeyIntMax() {
        return keyIntMax;
    }

    public H264Settings setKeyIntMax(Integer keyIntMax) {
        this.keyIntMax = keyIntMax;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + ", speedPreset=" + speedPreset +
                ", keyIntMax=" + keyIntMax;
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
