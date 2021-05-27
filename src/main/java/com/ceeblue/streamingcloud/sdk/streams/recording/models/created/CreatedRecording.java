package com.ceeblue.streamingcloud.sdk.streams.recording.models.created;

import com.ceeblue.streamingcloud.sdk.streams.recording.models.Capture;

import java.util.ArrayList;
import java.util.List;

public class CreatedRecording {
    String id;
    String streamId;
    String fileName;
    String fileFormat;
    String storageId;
    Capture capture;
    State state;
    int duration;
    int length;
    int uploaded;
    List<String> messages = new ArrayList<>();
    String callbackUri;

    public CreatedRecording() {
    }

    public String getId() {
        return id;
    }

    public CreatedRecording setId(String id) {
        this.id = id;
        return this;
    }

    public String getStreamId() {
        return streamId;
    }

    public CreatedRecording setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public CreatedRecording setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public CreatedRecording setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public String getStorageId() {
        return storageId;
    }

    public CreatedRecording setStorageId(String storageId) {
        this.storageId = storageId;
        return this;
    }

    public Capture getCapture() {
        return capture;
    }

    public CreatedRecording setCapture(Capture capture) {
        this.capture = capture;
        return this;
    }

    public State getState() {
        return state;
    }

    public CreatedRecording setState(State state) {
        this.state = state;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public CreatedRecording setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public int getLength() {
        return length;
    }

    public CreatedRecording setLength(int length) {
        this.length = length;
        return this;
    }

    public int getUploaded() {
        return uploaded;
    }

    public CreatedRecording setUploaded(int uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public CreatedRecording setMessages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public CreatedRecording setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }
}
