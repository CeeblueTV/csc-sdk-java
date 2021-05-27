package com.ceeblue.streamingcloud.sdk.streams.input.models.encoder;

import com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VP8Settings extends EncoderSettings {

    private Integer keyMaxDist;


    public VP8Settings() {
    }

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
