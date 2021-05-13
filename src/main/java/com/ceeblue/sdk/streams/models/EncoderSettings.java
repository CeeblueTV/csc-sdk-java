package com.ceeblue.sdk.streams.models;

import com.ceeblue.sdk.streams.utils.EncoderSettingsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EncoderSettingsDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncoderSettings {
    CodecName codec;
    Integer bitrate;

    public EncoderSettings(CodecName codec, Integer bitrate) {
        this.codec = codec;
        this.bitrate = bitrate;
    }

    public CodecName getCodec() {
        return codec;
    }

    public void setCodec(CodecName codec) {
        this.codec = codec;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    @Override
    public String toString() {
        return "EncoderSettings{" +
                "codec=" + codec +
                ", bitrate=" + bitrate +
                '}';
    }
}
