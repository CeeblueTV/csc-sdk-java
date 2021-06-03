package com.ceeblue.streamingcloud.sdk.streams.output;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.output.models.output.OutputEndpoint;
import com.ceeblue.streamingcloud.sdk.streams.output.models.output.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StingFormatter.getServerMessage;


public class OutputApiClientImplementation extends ApiClient implements OutputStreamClient {

    public static final String OUTPUTS = "/outputs";

    public static final String SESSIONS = "/sessions";

    public OutputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public OutputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public OutputEndpoint createOutput(Output output) throws ClientException {
        try {
            String json = createJson(output);

            return exchange(OUTPUTS, json, POST, OutputEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't create output stream: " + output, exception);
        } catch (ApiCallException exception) {
             String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't create output stream: " + output, exception);
        }

    }

    @Override
    public List <OutputEndpoint> getOutputs(String streamId) throws ClientException {
        String parts = OUTPUTS + "?streamId=" + streamId;
        try {
            OutputEndpoint[] result = exchange(parts, "", GET, OutputEndpoint[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get output stream", exception);
        } catch (ApiCallException exception) {
             String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get output stream", exception);
        }

        throw new ClientException("Can't get output stream", new RuntimeException("No result from server!!!"));
    }

    @Override
    public void deleteOutputSessions(String outputId) throws ClientException {
        try {
            exchange(OUTPUTS + "/" + outputId + SESSIONS, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete output stream", exception);
        }
    }

    @Override
    public void deleteOutput(String outputId) throws ClientException {
        try {
            exchange(OUTPUTS + "/" + outputId, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", exception);
        }
    }

}
