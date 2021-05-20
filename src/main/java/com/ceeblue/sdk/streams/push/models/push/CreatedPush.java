package com.ceeblue.sdk.streams.push.models.push;

import com.ceeblue.sdk.streams.input.models.InputFormat;
import com.ceeblue.sdk.streams.output.models.output.Output;
import com.ceeblue.sdk.streams.push.models.Status;

import java.util.List;

public class CreatedPush {
    String id;
    String streamId;
    InputFormat format;
    Output output;
    Status status;
    List<String> messages;

    public CreatedPush() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public InputFormat getFormat() {
        return format;
    }

    public void setFormat(InputFormat format) {
        this.format = format;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
