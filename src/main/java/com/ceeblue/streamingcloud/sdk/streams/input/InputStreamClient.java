package com.ceeblue.streamingcloud.sdk.streams.input;

import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.CreatedInput;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.util.List;

/**
 * Service for operations with input stream
 */
public interface InputStreamClient {

    /**
     * Create a new input stream on Ceeblue Cloud
     *
     * @param input parameter of InputStream. Input must contains {@link com.ceeblue.streamingcloud.sdk.streams.input.models.InputFormat}
     * @return {@link CreatedInput}
     * @throws ClientException if server return error
     */
    CreatedInput createInput(Input input) throws ClientException;

    /**
     * Fetch all input stream from server
     *
     * @return list of {@link CreatedInput}
     * @throws ClientException if server return error
     */
    List<CreatedInput> getInputs() throws ClientException;

    /**
     * Fetch input by id
     *
     * @return input from server {@link CreatedInput}
     * @throws ClientException if server return error
     */
    CreatedInput getInput(String id) throws ClientException;

    /**
     * Update stream state
     *
     * @param access is stream Public or Private {@link Access}
     * @param token  if stream private will set access token on stream
     * @return Created stream {@link CreatedInput}
     * @throws ClientException if server return error
     */
    CreatedInput updateInput(String id, Access access, String token) throws ClientException;

    /***
     * Delete input
     * @param id stream id
     * @throws ClientException if server return error
     */

    void deleteInput(String id) throws ClientException;

    /**
     * Fetch a specific output settings by stream id
     *
     * @param id stream id
     * @return {@link OutputSettings}
     * @throws ClientException if server return error
     */
    OutputSettings getOutputSettings(String id) throws ClientException;

    /**
     * Updates output settings by stream id.
     *
     * @param id stream id
     * @return {@link OutputSettings}
     * @throws ClientException if server return error
     */
    OutputSettings updateOutputSettings(String id, OutputSettings output) throws ClientException;
}
