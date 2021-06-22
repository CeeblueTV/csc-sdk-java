package com.ceeblue.streamingcloud.sdk.streams.input;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.input.models.Access;
import com.ceeblue.streamingcloud.sdk.streams.input.models.OutputSettings;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.Input;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StringFormatter.getServerMessage;

public class InputApiClientImplementation extends ApiClient implements InputStreamClient {

    public static final String INPUTS = "/inputs/";

    public static final String OUTPUT = "/output/";

    public InputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public InputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public InputEndpoint createInput(Input input) {
        try {
            String json = createJson(input);

            return exchange(INPUTS, json, POST, InputEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't create input stream: " + input, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't create input stream: " + input, exception);
        }
    }

    @Override
    public List<InputEndpoint> getInputs() {
        try {

            InputEndpoint[] result = exchange(INPUTS, "", GET, InputEndpoint[].class);
            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get stream", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get stream", exception);
        }

        throw new ClientException("Can't get stream", new RuntimeException("No result from server!!!"));
    }

    @Override
    public InputEndpoint getInput(String id) {
        try {
            return exchange(INPUTS + id, "", GET, InputEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get stream input", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get stream input", exception);
        }
    }

    @Override
    public InputEndpoint updateInput(String id, Access access, String token) {
        try {
            Map<String, Object> updated = new HashMap<>();
            if (id == null) {
                throw new IllegalArgumentException("Id must be nonnull");
            }
            if (access != null) {
                updated.put("access", access);
                updated.put("accessToken", token);
            }

            String body = createJson(updated);

            return exchange(INPUTS + id, body, PUT, InputEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't update stream", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't update stream", exception);
        }
    }

    @Override
    public void deleteInput(String id) {
        try {
            exchange(INPUTS + id, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", exception);
        }
    }

    @Override
    public OutputSettings getOutputSettings(String id) {
        try {
            return exchange(INPUTS + id + OUTPUT, "", GET, OutputSettings.class);

        } catch (JsonParseException exception) {
            throw new ClientException("Can't get output", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get output", exception);
        }
    }

    @Override
    public OutputSettings updateOutputSettings(String id, OutputSettings output) {
        try {
            String body = createJson(output);
            return exchange(INPUTS + id + OUTPUT, body, PUT, OutputSettings.class);

        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't update output. New output: " + output, exception);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't update output. New output: " + output, exception);
        }
    }
}
