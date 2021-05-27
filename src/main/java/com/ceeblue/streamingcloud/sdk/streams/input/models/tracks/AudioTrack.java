package com.ceeblue.streamingcloud.sdk.streams.input.models.tracks;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AudioTrack extends Track {
    /**
     * The sample rate output (8000-192000)
     */
    private Integer rate;
    /**
     * The output number of channels (1-2)
     */
    private Integer channels;

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
