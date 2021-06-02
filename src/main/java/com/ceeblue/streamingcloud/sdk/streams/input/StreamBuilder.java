package com.ceeblue.streamingcloud.sdk.streams.input;

import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.InputStreamer;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input;
import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;

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
        if (input.getAccess() == Access.Private) {
            input.setAccessToken(token);
        }
        return this;
    }


    public Input build() {
        return input;
    }

}
