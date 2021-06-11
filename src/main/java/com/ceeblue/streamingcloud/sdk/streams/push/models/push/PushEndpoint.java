package com.ceeblue.streamingcloud.sdk.streams.push.models.push;

import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;
import com.ceeblue.streamingcloud.sdk.streams.push.models.output.OutputParent;

import java.util.List;

public class PushEndpoint {

    /**
     * Push id
     */
    private String id;

    /**
     * Id of input stream {@see com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint}
     */
    private String streamId;

    /**
     * Push stream format [ RTMP | CMAF | UDPTS ]
     */
    private InputFormat format;

    /**
     * Push endpoint details
     */
    private OutputParent output;

    /**
     * Stream status
     */
    private Status status;

    /**
     * Starts pushing on stream state Ingestion
     */
    private boolean autostart;

    /**
     * Server messages e.g. errors
     */
    private List <String> messages;

    public PushEndpoint() {
    }

    public String getId() {
        return id;
    }

    public PushEndpoint setId(String id) {
        this.id = id;
        return this;
    }

    public String getStreamId() {
        return streamId;
    }

    public PushEndpoint setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public InputFormat getFormat() {
        return format;
    }

    public PushEndpoint setFormat(InputFormat format) {
        this.format = format;
        return this;
    }

    public OutputParent getOutput() {
        return output;
    }

    public PushEndpoint setOutput(OutputParent output) {
        this.output = output;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public PushEndpoint setStatus(Status status) {
        this.status = status;
        return this;
    }

    public List <String> getMessages() {
        return messages;
    }

    public PushEndpoint setMessages(List <String> messages) {
        this.messages = messages;
        return this;
    }

    private boolean isAutostart() {
        return autostart;
    }

    private PushEndpoint setAutostart(boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    @Override
    public String toString() {
        return "CreatedPush{" +
                (id != null ? "id='" + id + '\'' : "") +
                (streamId != null ? ", streamId='" + streamId + '\'' : "") +
                (format != null ? ", format=" + format : "") +
                (output != null ? ", output=" + output : "") +
                (status != null ? ", status=" + status : "") +
                (messages != null ? ", messages=" + messages : "") +
                '}';
    }
}
