package com.ceeblue.streamingcloud.sdk.streams.recording.models.created;

import com.ceeblue.streamingcloud.sdk.streams.recording.models.Capture;
import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;

import java.util.ArrayList;
import java.util.List;

public class RecordingModel {

    /**
     * Recording identifier
     */
    private String id;

    /**
     * Stream identifier
     */
    private String streamId;

    /**
     * Target file name
     */
    private String fileName;

    /**
     * Target file format [ MKV ]
     */
    private String fileFormat;

    /**
     * Storage identifier {@link AmazonS3}
     */
    private String storageId;

    /**
     * Capturing settings (Capture)
     */
    private Capture capture;

    /**
     * Recording state [ Recording | Uploading | Completed | Error ]
     */
    private State state;

    /**
     * Recording duration (seconds)
     */
    private Integer duration;

    /**
     * Recording file length (bytes
     */
    private Integer length;

    /**
     * Amount of recorded file uploaded to storage (bytes)
     */
    private Integer uploaded;

    /**
     * List of messages
     */
    private List <String> messages = new ArrayList <>();

    /**
     * Invokes on state change. (HTTP POST)
     */
    private String callbackUri;

    public RecordingModel() {
    }

    public String getId() {
        return id;
    }

    public RecordingModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getStreamId() {
        return streamId;
    }

    public RecordingModel setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public RecordingModel setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public RecordingModel setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public String getStorageId() {
        return storageId;
    }

    public RecordingModel setStorageId(String storageId) {
        this.storageId = storageId;
        return this;
    }

    public Capture getCapture() {
        return capture;
    }

    public RecordingModel setCapture(Capture capture) {
        this.capture = capture;
        return this;
    }

    public State getState() {
        return state;
    }

    public RecordingModel setState(State state) {
        this.state = state;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public RecordingModel setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public RecordingModel setLength(int length) {
        this.length = length;
        return this;
    }

    public Integer getUploaded() {
        return uploaded;
    }

    public RecordingModel setUploaded(int uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public List <String> getMessages() {
        return messages;
    }

    public RecordingModel setMessages(List <String> messages) {
        this.messages = messages;
        return this;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public RecordingModel setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
        return this;
    }

    @Override
    public String toString() {
        return "RecordingModel{ " +
                (id != null ? "id=" + id : "") +
                (streamId != null ? ", streamId=" + streamId : "") +
                (fileName != null ? ", fileName=" + fileName : "") +
                (fileFormat != null ? ", fileFormat=" + fileFormat : "") +
                (storageId != null ? ", storageId=" + storageId : "") +
                (capture != null ? ", capture=" + capture : "") +
                (state != null ? ", state=" + state : "") +
                (duration != null ? ", duration=" + duration : "") +
                (length != null ? ", length=" + length : "") +
                (uploaded != null ? ", uploaded=" + uploaded : "") +
                (messages != null ? ", messages=" + messages : "") +
                (callbackUri != null ? ", callbackUri=" + callbackUri : "") +
                " }";
    }
}
