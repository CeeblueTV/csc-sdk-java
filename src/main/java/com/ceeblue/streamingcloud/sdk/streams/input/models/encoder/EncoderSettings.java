package com.ceeblue.streamingcloud.sdk.streams.input.models.encoder;

import com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "codec", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = H264Settings.class, name = "H264"),
        @JsonSubTypes.Type(value = VP8Settings.class, name = "VP8"),
        @JsonSubTypes.Type(value = EncoderSettings.class, name = "AAC"),
        @JsonSubTypes.Type(value = EncoderSettings.class, name = "Opus"),
        @JsonSubTypes.Type(value = EncoderSettings.class, name = "MP3")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncoderSettings {

    /**
     * Codec name
     * Required
     */
    protected CodecName codec;

    /**
     * Bitrate in kbit/sec (Min 100)
     * Required
     */
    private Integer bitrate;

    public EncoderSettings() {

    }

    public EncoderSettings(CodecName codec, Integer bitrate) {
        this.codec = codec;
        this.bitrate = bitrate;
    }

    public CodecName getCodec() {
        return codec;
    }

    public EncoderSettings setCodec(CodecName codec) {
        this.codec = codec;
        return this;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public EncoderSettings setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    @Override
    public String toString() {
        return "codec=" + codec +
                ", bitrate=" + bitrate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncoderSettings settings = (EncoderSettings) o;
        return codec == settings.codec && Objects.equals(bitrate, settings.bitrate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codec, bitrate);
    }

}
