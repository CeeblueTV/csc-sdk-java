package com.ceeblue.streamingcloud.sdk.streams.input.utils;

import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.EncoderSettings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.H264Settings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.VP8Settings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.AudioTrack;
import com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.Track;
import com.ceeblue.streamingcloud.sdk.streams.input.models.tracks.VideoTrack;

import static com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName.AAC;
import static com.ceeblue.streamingcloud.sdk.streams.input.models.CodecName.Opus;
import static com.ceeblue.streamingcloud.sdk.streams.input.models.encoder.SpeedPreset.superfast;

public class Presets {

    public static Track Opus_48kHz_2Ch_120Kbps() {
        EncoderSettings settings = new EncoderSettings()
                .setCodec(Opus)
                .setBitrate(128);

        return new AudioTrack(settings)
                .setChannels(2)
                .setRate(48000);
    }

    public static Track AAC_48kHz_2Ch_120Kbps() {
        EncoderSettings settings = new EncoderSettings()
                .setCodec(AAC)
                .setBitrate(128);

        return new AudioTrack(settings)
                .setChannels(2)
                .setRate(48000);
    }

    public static Track VP8_240p_30fps_500Kbps() {
        EncoderSettings settings = new VP8Settings()
                .setKeyMaxDist(60)
                .setBitrate(500);

        return new VideoTrack(settings)
                .setHeight(240)
                .setWidth(426)
                .setFramerate(30);
    }

    public static Track VP8_360p_30fps_800Kbps() {
        EncoderSettings settings = new VP8Settings()
                .setKeyMaxDist(60)
                .setBitrate(800);

        return new VideoTrack(settings)
                .setHeight(360)
                .setWidth(640)
                .setFramerate(30);
    }

    public static Track VP8_480p_30fps_1_2Mbps() {
        EncoderSettings settings = new VP8Settings()
                .setKeyMaxDist(60)
                .setBitrate(1200);

        return new VideoTrack(settings)
                .setHeight(480)
                .setWidth(854)
                .setFramerate(30);
    }

    public static Track VP8_720p_30fps_2Mbps() {
        EncoderSettings settings = new VP8Settings()
                .setKeyMaxDist(60)
                .setBitrate(2000);

        return new VideoTrack(settings)
                .setHeight(720)
                .setWidth(1280)
                .setFramerate(30);
    }

    public static Track VP8_1080p_30fps_4Mbps() {
        EncoderSettings settings = new VP8Settings()
                .setKeyMaxDist(60)
                .setBitrate(4000);

        return new VideoTrack(settings)
                .setHeight(1080)
                .setWidth(1920)
                .setFramerate(30);
    }

    public static Track H264_240p_30fps_500Kbps() {
        EncoderSettings settings = new H264Settings()
                .setKeyIntMax(60)
                .setSpeedPreset(superfast)
                .setBitrate(500);

        return new VideoTrack(settings)
                .setHeight(240)
                .setWidth(426)
                .setFramerate(30);
    }

    public static Track H264_360p_30fps_800Kbps() {
        EncoderSettings settings = new H264Settings()
                .setKeyIntMax(60)
                .setSpeedPreset(superfast)
                .setBitrate(800);

        return new VideoTrack(settings)
                .setHeight(360)
                .setWidth(640)
                .setFramerate(30);
    }

    public static Track H264_480p_30fps_1_2Mbps() {
        EncoderSettings settings = new H264Settings()
                .setKeyIntMax(60)
                .setSpeedPreset(superfast)
                .setBitrate(1200);

        return new VideoTrack(settings)
                .setHeight(480)
                .setWidth(854)
                .setFramerate(30);
    }

    public static Track H264_720p_30fps_2Mbps() {
        EncoderSettings settings = new H264Settings()
                .setKeyIntMax(60)
                .setSpeedPreset(superfast)
                .setBitrate(2000);

        return new VideoTrack(settings)
                .setHeight(720)
                .setWidth(1280)
                .setFramerate(30);
    }

    public static Track H264_1080p_30fps_4Mbps() {
        EncoderSettings settings = new H264Settings()
                .setKeyIntMax(60)
                .setSpeedPreset(superfast)
                .setBitrate(4000);

        return new VideoTrack(settings)
                .setHeight(1080)
                .setWidth(1920)
                .setFramerate(30);
    }

}

