package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.input.models.Access;
import com.ceeblue.sdk.streams.input.models.inputs.CreatedInput;
import com.ceeblue.sdk.streams.input.models.OutputSettings;
import com.ceeblue.sdk.streams.input.models.inputs.Input;
import com.ceeblue.sdk.utils.ClientException;

import java.util.List;

public interface InputStreamClient {

    CreatedInput createStream(Input input) throws ClientException;

    List<CreatedInput> getInputs() throws ClientException;

    CreatedInput getInput(String id) throws ClientException;

    CreatedInput updateInput(String id, Access access, String token) throws ClientException;

    void deleteInput(String id) throws ClientException;

    OutputSettings getOutputSettings(String id) throws ClientException;

    OutputSettings updateOutputSettings(String id, OutputSettings output) throws ClientException;

    void setTemplate(HttpClient template);
}
