package com.ceeblue.streamingcloud.sdk.streams.snapshot.models;

public class Snapshot {
    /**
     * Target file format
     * Required
     */
    private SnapshotFormatType format;
    /**
     * Target file quality
     * Required
     */
    private SnapshotQualityType quality;
    /**
     * Snapshot producing interval [ 1 - 30 ]
     * Required
     */
    private int interval;

    public Snapshot(SnapshotFormatType format, SnapshotQualityType quality, int interval) {
        this.format = format;
        this.quality = quality;
        this.interval = interval;
    }

    public Snapshot() {
    }

    public SnapshotFormatType getFormat() {
        return format;
    }

    public Snapshot setFormat(SnapshotFormatType format) {
        this.format = format;
        return this;
    }

    public SnapshotQualityType getQuality() {
        return quality;
    }

    public Snapshot setQuality(SnapshotQualityType quality) {
        this.quality = quality;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public Snapshot setInterval(int interval) {
        this.interval = interval;
        return this;
    }
}
