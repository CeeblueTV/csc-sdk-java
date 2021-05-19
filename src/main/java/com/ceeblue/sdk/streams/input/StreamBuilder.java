package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.streams.input.models.*;

import static com.ceeblue.sdk.streams.input.models.Access.Private;

public class StreamBuilder {
    Stream stream = new Stream();

    public StreamBuilder(InputFormat format) {
        stream.setFormat(format);
    }

    public StreamBuilder setInputStreamer(InputStreamer streamer) {
        stream.setStreamer(streamer);
        return this;
    }

    public StreamBuilder setCallbackUri(String callbackUri) {
        stream.setCallbackUri(callbackUri);
        return this;
    }

    public StreamBuilder setOutput(OutputSettings output) {
        stream.setOutput(output);
        return this;
    }

    public StreamBuilder setAccess(Access access, String token) {
        stream.setAccess(access);
        if (stream.getAccess() == Private) {
            stream.setAccessToken(token);
        }
        return this;
    }


    public Stream build() {
        return stream;
    }
}
