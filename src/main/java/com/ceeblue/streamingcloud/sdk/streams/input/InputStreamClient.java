package com.ceeblue.streamingcloud.sdk.streams.input;

import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.CreatedInput;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;

import java.util.List;

/**
 * Client for input stream manipulation
 */
public interface InputStreamClient {

    /**
     * Create a new input stream on Ceeblue Cloud
     *
     * @param input parameter of InputStream.
     * @return created input
     */
    CreatedInput createInput(Input input) throws ClientException;

    /**
     * Fetch all input stream from server
     *
     * @return list of input stream
     */
    List <CreatedInput> getInputs() throws ClientException;

    /**
     * Fetch input by id
     *
     * @return input stream
     */
    CreatedInput getInput(String id) throws ClientException;

    /**
     * Update stream state
     *
     * @param access is stream Public or Private
     * @param token  if stream private will set access token on stream
     * @return Created stream {@link CreatedInput}
     */
    CreatedInput updateInput(String id, Access access, String token) throws ClientException;

    /***
     * Delete input
     * @param id input stream id
     */
    void deleteInput(String id) throws ClientException;

    /**
     * Fetch a specific output settings by stream id
     *
     * @param id stream id
     * @return output settings
     */
    OutputSettings getOutputSettings(String id) throws ClientException;

    /**
     * Updates output settings by stream id.
     *
     * @param id stream id
     * @return output settings
     */
    OutputSettings updateOutputSettings(String id, OutputSettings output) throws ClientException;

}
