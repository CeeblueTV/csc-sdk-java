package com.ceeblue.streamingcloud.sdk.streams.output;

import com.ceeblue.streamingcloud.sdk.streams.output.models.output.CreatedOutput;
import com.ceeblue.streamingcloud.sdk.streams.output.models.output.Output;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.util.List;

/**
 * Client for input output manipulation
 */
public interface OutputStreamClient {
    /**
     * Create output for {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input}
     *
     * @param output output that will be applied. Must contains: streamId, {@link com.ceeblue.streamingcloud.sdk.streams.input.models.InputFormat}
     * @return {@link CreatedOutput}}
     */
    CreatedOutput createOutput(Output output) throws ClientException;

    /**
     * Fetch output of certain stream
     *
     * @param streamId id of input stream
     * @return output list
     */
    List<CreatedOutput> getOutputs(String streamId) throws ClientException;

    /***
     * Invalidate all active sessions
     *
     * @param outputId id of output stream
     */
    void deleteOutputSessions(String outputId) throws ClientException;

    /**
     * delete an output from the Ceeblue Cloud
     *
     * @param outputId id of output
     */
    void deleteOutput(String outputId) throws ClientException;
}
