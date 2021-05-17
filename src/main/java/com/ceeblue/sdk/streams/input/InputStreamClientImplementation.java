package com.ceeblue.sdk.streams.input;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.StreamClient;
import com.ceeblue.sdk.streams.input.models.Access;
import com.ceeblue.sdk.streams.input.models.CreatedStream;
import com.ceeblue.sdk.streams.input.models.Output;
import com.ceeblue.sdk.streams.input.models.Stream;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

@Service
public class InputStreamClientImplementation extends StreamClient implements InputStreamClient {

    public static final String INPUTS = "/inputs/";
    public static final String OUTPUT = "/output/";

    @Autowired
    public InputStreamClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public InputStreamClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedStream createStream(Stream stream) {
        try {
            String json = createJson(stream);

            return exchange(INPUTS, json, POST, CreatedStream.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create stream: " + stream, session + INPUTS, POST, exception);
        }
    }

    @Override
    public List<CreatedStream> getInputs() {
        try {

            CreatedStream[] result = exchange(INPUTS, "", GET, CreatedStream[].class);
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
    public CreatedStream getInput(String id) {
        try {
            return exchange(INPUTS + id, "", GET, CreatedStream.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream input", session + INPUTS + id, GET, exception);
        }
    }


    @Override
    public CreatedStream updateInput(String id, Access access, String token) {
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

            return exchange(INPUTS + id, body, PUT, CreatedStream.class);
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
    public Output getOutput(String id) {
        try {
            return exchange(INPUTS + id + OUTPUT, "", GET, Output.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get output", session + INPUTS + id + OUTPUT, GET, exception);
        }
    }

    @Override
    public Output updateOutput(String id, Output output) {
        try {
            String body = createJson(output);
            return exchange(INPUTS + id + OUTPUT, body, PUT, Output.class);

        } catch (ApiCallException | JsonParseException exception) {
            throw new ClientException("Can't update output. New output: " + output, session.getEndpoint() + INPUTS + id + OUTPUT, PUT, exception);
        }
    }

    public void setTemplate(HttpClient template) {
        this.template = template;
    }
}
