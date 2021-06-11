package com.ceeblue.streamingcloud.sdk.streams.input.models.tracks;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.TrackType.Audio;

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

    public AudioTrack(EncoderSettings settings) {
        super(Audio, settings);
    }

    private AudioTrack() {
    }

    public Integer getRate() {
        return rate;
    }

    public AudioTrack setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public Integer getChannels() {
        return channels;
    }

    public AudioTrack setChannels(Integer channels) {
        this.channels = channels;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", rate=" + rate +
                ", channels=" + channels +
                "}";
    }
}
