package com.ceeblue.sdk.streams.output;

import com.ceeblue.sdk.streams.output.models.output.CreatedOutput;
import com.ceeblue.sdk.streams.output.models.output.Output;

import java.util.List;

public interface OutputStreamClient {

    CreatedOutput createOutput(Output output);

    List<CreatedOutput> getOutputs(String streamId);

    void deleteOutputSessions(String outputId);

    void deleteOutput(String outputId);
}
