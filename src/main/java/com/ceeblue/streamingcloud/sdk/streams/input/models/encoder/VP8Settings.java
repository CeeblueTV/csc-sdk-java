package com.ceeblue.streamingcloud.sdk.streams.input.models.encoder;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName.VP8;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VP8Settings extends EncoderSettings {

    /**
     * Maximal distance between two key-frames (Min 2)
     * Required
     */
    private Integer keyMaxDist;

    public VP8Settings() {
        codec = VP8;
    }

    public VP8Settings(Integer bitrate, Integer keyMaxDist) {
        super(VP8, bitrate);
        this.keyMaxDist = keyMaxDist;
    }

    public Integer getKeyMaxDist() {
        return keyMaxDist;
    }

    public VP8Settings setKeyMaxDist(Integer keyMaxDist) {
        this.keyMaxDist = keyMaxDist;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + ", keyMaxDist=" + keyMaxDist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VP8Settings that = (VP8Settings) o;
        return Objects.equals(keyMaxDist, that.keyMaxDist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), keyMaxDist);
    }
}
