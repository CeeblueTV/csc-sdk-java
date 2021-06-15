package com.ceeblue.streamingcloud.sdk.streams.output;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.models.InputFormat;
import com.ceeblue.streamingcloud.sdk.streams.output.models.output.Output;
import com.ceeblue.streamingcloud.sdk.streams.output.models.output.OutputEndpoint;

import java.util.List;

/**
 * Client for output manipulation
 */
public interface OutputStreamClient {

    /**
     * Create output for {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input}
     *
     * @param output output that will be applied. Must contains: streamId, {@link InputFormat}
     * @return {@link OutputEndpoint}}
     */
    OutputEndpoint createOutput(Output output) throws ClientException;

    /**
     * Fetch output of certain stream
     *
     * @param streamId id of input stream
     * @return output list
     */
    List<OutputEndpoint> getOutputs(String streamId) throws ClientException;

    /**
     * Invalidate all active sessions
     *
     * @param outputId id of output stream
     */
    void deleteOutputSessions(String outputId) throws ClientException;

    /**
     * Delete an output from the Ceeblue Cloud
     *
     * @param outputId id of output
     */
    void deleteOutput(String outputId) throws ClientException;
}
