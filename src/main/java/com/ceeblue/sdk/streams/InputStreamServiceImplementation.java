package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.authentiffication.AuthenticationService;
import com.ceeblue.sdk.http.template.HttpTemplate;
import com.ceeblue.sdk.settings.Settings;
import com.ceeblue.sdk.streams.models.*;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.streams.models.CodecName.AAC;
import static com.ceeblue.sdk.streams.models.CodecName.VP8;
import static com.ceeblue.sdk.streams.models.TrackType.Audio;
import static com.ceeblue.sdk.streams.models.TrackType.Video;

@Service
public class InputStreamServiceImplementation implements InputStreamService {

    public static final String INPUTS = "/inputs/";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String OUTPUT = "/output/";
    private final AuthenticationService authenticationService;
    private final Settings settings;

    private final HttpTemplate template;

    public InputStreamServiceImplementation(AuthenticationService authenticationService, Settings settings, HttpTemplate template) {
        this.authenticationService = authenticationService;
        this.settings = settings;
        this.template = template;
    }

    @Override
    public CreatedStream createStream(Stream stream) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(stream);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse passed stream for creation. Stream: " + stream, e);
        }

        authenticate();
        return template.post(CreatedStream.class, settings.getApi() + INPUTS, json, new HashMap<>());
    }

    @Override
    public List<CreatedStream> getInputs() {
        authenticate();
        CreatedStream[] inputs = template.get(CreatedStream[].class, settings.getApi() + INPUTS);
        return Arrays.stream(Objects.requireNonNull(inputs)).collect(Collectors.toList());
    }

    @Override
    public CreatedStream getInput(String id) {
        try {
            authenticate();
            return template.get(CreatedStream.class, settings.getApi() + INPUTS + id);
        } catch (HttpStatusCodeException exception) {
            throw new ApiCallException("Can't find input with id: " + id, exception.getStatusCode().value(), exception.getResponseBodyAsString());
        }
    }


    @Override
    public CreatedStream updateInput(String id, Access access, String token) {
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
        try {
            String body = mapper.writeValueAsString(updated);

            authenticate();
            return template.put(CreatedStream.class, settings.getApi() + INPUTS + id, body, new HashMap<>());
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + updated, e);
        }
    }

    @Override
    public void deleteInput(String id) {
        authenticate();
        template.delete(String.class, settings.getApi() + INPUTS + id);
    }

    @Override
    public Output getOutput(String id) {
        try {
            authenticate();
            return template.get(Output.class, settings.getApi() + INPUTS + id + OUTPUT);
        } catch (HttpStatusCodeException exception) {
            throw new ApiCallException("Can't find input with id: " + id, exception.getStatusCode().value(), exception.getResponseBodyAsString());
        }
    }

    @Override
    public Output updateOutput(String id, Output output) {
        try {
            try {
                String body = mapper.writeValueAsString(output);
                authenticate();
                return template.put(Output.class, settings.getApi() + INPUTS + id + OUTPUT, body, new HashMap<>());
            } catch (JsonProcessingException e) {
                throw new JsonParseException("Can't create json from passed parameters. Params: " + output, e);
            }
        } catch (HttpStatusCodeException exception) {
            throw new ApiCallException("Can't find input with id: " + id, exception.getStatusCode().value(), exception.getResponseBodyAsString());
        }
    }

    public void authenticate() {
        if (settings.getToken() == null) {
            template.authorize(authenticationService.getOrCreateToken());
        }
    }
}
