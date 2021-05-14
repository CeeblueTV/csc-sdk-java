package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.streams.models.Access;
import com.ceeblue.sdk.streams.models.CreatedStream;
import com.ceeblue.sdk.streams.models.Output;
import com.ceeblue.sdk.streams.models.Stream;
import com.ceeblue.sdk.utils.ClientException;

import java.util.List;

public interface InputStreamClient {
    CreatedStream createStream(Stream stream);

    List<CreatedStream> getInputs();

    CreatedStream getInput(String id);

    CreatedStream updateInput(String id, Access access, String token);

    void deleteInput(String id);

    Output getOutput(String id);

    Output updateOutput(String id, Output output) throws ClientException;
}
