package com.ceeblue.sdk.streams.input.models.tracks;

import com.ceeblue.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AudioTrack extends Track {

    Integer rate;
    Integer channels;

    public AudioTrack(TrackType type, EncoderSettings settings) {
        super(type, settings);
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }
}
