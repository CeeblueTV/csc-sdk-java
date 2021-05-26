package com.ceeblue.sdk.streams.snapshot.models;

public class Recording {
   private SnapshotFormatType format;
   private SnapshotQualityType quality;
   private int interval;

    public Recording(SnapshotFormatType format, SnapshotQualityType quality, int interval) {
        this.format = format;
        this.quality = quality;
        this.interval = interval;
    }

    public Recording() {
    }

    public SnapshotFormatType getFormat() {
        return format;
    }

    public Recording setFormat(SnapshotFormatType format) {
        this.format = format;
        return this;
    }

    public SnapshotQualityType getQuality() {
        return quality;
    }

    public Recording setQuality(SnapshotQualityType quality) {
        this.quality = quality;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public Recording setInterval(int interval) {
        this.interval = interval;
        return this;
    }
}
