package com.ceeblue.streamingcloud.sdk.streams.snapshot.models;

public class SnapshotSettings {

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

    public SnapshotSettings(SnapshotFormatType format, SnapshotQualityType quality, int interval) {
        this.format = format;
        this.quality = quality;
        this.interval = interval;
    }

    public SnapshotSettings() {
    }

    public SnapshotFormatType getFormat() {
        return format;
    }

    public SnapshotSettings setFormat(SnapshotFormatType format) {
        this.format = format;
        return this;
    }

    public SnapshotQualityType getQuality() {
        return quality;
    }

    public SnapshotSettings setQuality(SnapshotQualityType quality) {
        this.quality = quality;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public SnapshotSettings setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "format=" + format +
                ", quality=" + quality +
                ", interval=" + interval +
                '}';
    }
}
