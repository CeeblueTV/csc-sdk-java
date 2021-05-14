package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.streams.models.Access;
import com.ceeblue.sdk.streams.models.CreatedStream;
import com.ceeblue.sdk.streams.models.Output;
import com.ceeblue.sdk.streams.models.Stream;

import java.util.List;

public interface InputStreamService {
    CreatedStream createStream(Stream stream);

    List<CreatedStream> getInputs();

    CreatedStream getInput(String id);

    CreatedStream updateInput(String id, Access access, String token);

    void deleteInput(String id);

    Output getOutput(String id);

    Output updateOutput(String id, Output output);
}
