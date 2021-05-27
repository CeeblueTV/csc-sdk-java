package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.ApiClient;
import com.ceeblue.sdk.streams.input.models.Access;
import com.ceeblue.sdk.streams.input.models.OutputSettings;
import com.ceeblue.sdk.streams.input.models.inputs.CreatedInput;
import com.ceeblue.sdk.streams.input.models.inputs.Input;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

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
    public CreatedInput createInput(Input input) {
        try {
            String json = createJson(input);

            return exchange(INPUTS, json, POST, CreatedInput.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create input stream: " + input, session + INPUTS, POST, exception);
        }
    }

    @Override
    public List<CreatedInput> getInputs() {
        try {

            CreatedInput[] result = exchange(INPUTS, "", GET, CreatedInput[].class);
            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream", session + INPUTS, GET, exception);
        }

        throw new ClientException("Can't get stream", session + INPUTS, GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public CreatedInput getInput(String id) {
        try {
            return exchange(INPUTS + id, "", GET, CreatedInput.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream input", session + INPUTS + id, GET, exception);
        }
    }


    @Override
    public CreatedInput updateInput(String id, Access access, String token) {
        try {
            Map<String, Object> updated = new HashMap<>();
            if (id == null) {
                throw new IllegalArgumentException("Id must be nonnull");
            }
            if (access != null) {
                updated.put("access", access);
            }
            if (access != null) {
                updated.put("accessToken", token);
            }

            String body = createJson(updated);

            return exchange(INPUTS + id, body, PUT, CreatedInput.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't update stream", INPUTS + id + INPUTS + id, PUT, exception);
        }
    }

    @Override
    public void deleteInput(String id) {
        try {
            exchange(INPUTS + id, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", session + INPUTS + id, DELETE, exception);
        }
    }

    @Override
    public OutputSettings getOutputSettings(String id) {
        try {
            return exchange(INPUTS + id + OUTPUT, "", GET, OutputSettings.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get output", session + INPUTS + id + OUTPUT, GET, exception);
        }
    }

    @Override
    public OutputSettings updateOutputSettings(String id, OutputSettings output) {
        try {
            String body = createJson(output);
            return exchange(INPUTS + id + OUTPUT, body, PUT, OutputSettings.class);

        } catch (ApiCallException | JsonParseException exception) {
            throw new ClientException("Can't update output. New output: " + output, session.getEndpoint() + INPUTS + id + OUTPUT, PUT, exception);
        }
    }

    public void setTemplate(HttpClient template) {
        this.template = template;
    }
}
