package com.ceeblue.streamingcloud.sdk.streams.recording.models;

import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;

public class Recording {

    /**
     * Stream identifier {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input}
     * Required
     */
    private String streamId;

    /**
     * Target file name
     * Required
     */
    private String fileName;

    /**
     * Target file format
     * Required
     */
    private FileFormat fileFormat;

    /**
     * Stream capture settings
     * Required
     */
    private Capture capture;

    /**
     * Storage identifier {@link AmazonS3}
     * Required
     */
    private String storageId;

    /**
     * Invokes on state change. (HTTP POST)
     */
    private String callbackUri;

    /**
     * Constructor with all required fields
     */
    public Recording(String streamId, String fileName, FileFormat fileFormat, Capture capture, String storageId) {
        this.streamId = streamId;
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.capture = capture;
        this.storageId = storageId;
    }

    public Recording() {
    }

    public String getStreamId() {
        return streamId;
    }

    public Recording setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Recording setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileFormat getFileFormat() {
        return fileFormat;
    }

    public Recording setFileFormat(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public Capture getCapture() {
        return capture;
    }

    public Recording setCapture(Capture capture) {
        this.capture = capture;
        return this;
    }

    public String getStorageId() {
        return storageId;
    }

    public Recording setStorageId(String storageId) {
        this.storageId = storageId;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public Recording setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

}
