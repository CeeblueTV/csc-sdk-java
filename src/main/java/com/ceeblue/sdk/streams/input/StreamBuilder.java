package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.streams.input.models.InputFormat;
import com.ceeblue.sdk.streams.input.models.InputStreamer;
import com.ceeblue.sdk.streams.input.models.Output;
import com.ceeblue.sdk.streams.input.models.Stream;

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

    public StreamBuilder setOutput(Output output) {
        stream.setOutput(output);
        return this;
    }

    public StreamBuilder setAccessToken(String format) {
        stream.setAccessToken(format);
        return this;
    }


    public Stream build() {
        return stream;
    }
}
