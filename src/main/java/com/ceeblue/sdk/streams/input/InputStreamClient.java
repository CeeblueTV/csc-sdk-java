package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.input.models.Access;
import com.ceeblue.sdk.streams.input.models.CreatedStream;
import com.ceeblue.sdk.streams.input.models.OutputSettings;
import com.ceeblue.sdk.streams.input.models.Stream;
import com.ceeblue.sdk.utils.ClientException;

import java.util.List;

public interface InputStreamClient {

    CreatedStream createStream(Stream stream) throws ClientException;

    List<CreatedStream> getInputs() throws ClientException;

    CreatedStream getInput(String id) throws ClientException;

    CreatedStream updateInput(String id, Access access, String token) throws ClientException;

    void deleteInput(String id) throws ClientException;

    OutputSettings getOutputSettings(String id) throws ClientException;

    OutputSettings updateOutput(String id, OutputSettings output) throws ClientException;

    void setTemplate(HttpClient template);
}
