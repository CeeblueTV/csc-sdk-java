package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.streams.input.models.*;
import com.ceeblue.sdk.streams.input.models.inputs.Input;

import static com.ceeblue.sdk.streams.input.models.Access.Private;

public class StreamBuilder {
    Input input = new Input();

    public StreamBuilder(InputFormat format) {
        input.setFormat(format);
    }

    public StreamBuilder setInputStreamer(InputStreamer streamer) {
        input.setStreamer(streamer);
        return this;
    }

    public StreamBuilder setCallbackUri(String callbackUri) {
        input.setCallbackUri(callbackUri);
        return this;
    }

    public StreamBuilder setOutput(OutputSettings output) {
        input.setOutput(output);
        return this;
    }

    public StreamBuilder setAccess(Access access, String token) {
        input.setAccess(access);
        if (input.getAccess() == Private) {
            input.setAccessToken(token);
        }
        return this;
    }


    public Input build() {
        return input;
    }
}
