package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.streams.models.InputFormat;
import com.ceeblue.sdk.streams.models.InputStreamer;
import com.ceeblue.sdk.streams.models.Output;
import com.ceeblue.sdk.streams.models.Stream;

public class StreamBuilder {
    Stream stream = new Stream();

    public StreamBuilder(InputFormat format) {
        stream.setFormat(format);
    }

    StreamBuilder setInputStreamer(InputStreamer streamer) {
        stream.setStreamer(streamer);
        return this;
    }

    StreamBuilder setCallbackUri(String callbackUri) {
        stream.setCallbackUri(callbackUri);
        return this;
    }

    StreamBuilder setOutput(Output output) {
        stream.setOutput(output);
        return this;
    }

    StreamBuilder setAccessToken(String format) {
        stream.setAccessToken(format);
        return this;
    }


    public Stream build() {
        return stream;
    }
}
