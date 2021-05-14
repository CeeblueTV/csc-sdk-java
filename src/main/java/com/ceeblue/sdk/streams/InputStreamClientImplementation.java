package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.authentiffication.Session;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.http.RequestInfo;
import com.ceeblue.sdk.http.template.utils.HTTPMethod;
import com.ceeblue.sdk.http.template.utils.MediaType;
import com.ceeblue.sdk.streams.models.*;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.AUTHORIZATION_HEADER;
import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.BEARER;
import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;
import static com.ceeblue.sdk.streams.models.CodecName.AAC;
import static com.ceeblue.sdk.streams.models.CodecName.H264;
import static com.ceeblue.sdk.streams.models.TrackType.Audio;
import static com.ceeblue.sdk.streams.models.TrackType.Video;

@Service
public class InputStreamClientImplementation implements InputStreamClient {

    public static final String INPUTS = "/inputs/";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String OUTPUT = "/output/";
    private final AuthenticationClient authenticationClient;
    private HttpClient template;
    private Session session;

    public InputStreamClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        this.authenticationClient = authenticationClient;
        this.template = template;
    }

    @PostConstruct
    public void init() {
        try {
            StreamBuilder builder = new StreamBuilder(InputFormat.WebRTC)
                    .setOutput(
                            new Output(true)
                                    .version(1)
                                    .tracks(Arrays.asList(
                                            new VideoTrack(Video, new H264Settings(H264, 110, SpeedPreset.faster, 20)),
                                            new AudioTrack(Audio, new EncoderSettings(AAC, 30))
                                    )));

            Stream stream = builder.build();
            CreatedStream createdStream = createStream(stream);
            System.out.println("Created:\n" + createdStream);
            System.out.println("All Streams:\n" + getInputs());
            System.out.println("Result of creating:\n" + getInput(createdStream.getId()));

            createdStream = updateInput(createdStream.getId(), Access.Private, null);
            System.out.println("Result of update:\n" + getInput(createdStream.getId()));

            Output output = getOutput(createdStream.getId());
            System.out.println("Output: \n" + output);
            output.setPassthrough(false);
            System.out.println("Result of update output:\n" + updateOutput(createdStream.getId(), output));

            deleteInput(createdStream.getId());

            System.out.println("Result of delete. Expected ClientException. Result:");
            try {
                System.out.println(getInput(createdStream.getId()));
            } catch (ApiCallException exception) {
                System.out.println(exception);
            }

        } catch (RuntimeException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public CreatedStream createStream(Stream stream) throws ClientException {
        try {
            String json = createJson(stream);

            return exchange(INPUTS, json, POST, CreatedStream.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create stream: " + stream, session + INPUTS, POST, exception);
        }
    }

    @Override
    public List<CreatedStream> getInputs() throws ClientException {
        try {

            return Arrays.stream(exchange(INPUTS, "", GET, CreatedStream[].class))
                    .collect(Collectors.toList());

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream", session + INPUTS, GET, exception);

        }

    }

    @Override
    public CreatedStream getInput(String id) throws ClientException {
        try {
            return exchange(INPUTS + id, "", GET, CreatedStream.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream", session + INPUTS + id, GET, exception);
        }
    }


    @Override
    public CreatedStream updateInput(String id, Access access, String token) throws ClientException {
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
    public void deleteInput(String id) throws ClientException {
        try {
            exchange(INPUTS + id, "", DELETE, Void.class);

        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", session + INPUTS + id, DELETE, exception);
        }
    }

    @Override
    public Output getOutput(String id) throws ClientException {
        try {
            return exchange(INPUTS + id + OUTPUT, "", GET, Output.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get output", session + INPUTS + id + OUTPUT, GET, exception);
        }
    }

    @Override
    public Output updateOutput(String id, Output output) throws ClientException {
        try {
            String body = createJson(output);
            return exchange(INPUTS + id + OUTPUT, body, PUT, Output.class);

        } catch (ApiCallException | JsonParseException exception) {
            throw new ClientException("Can't update output. New output: " + output, session.getEndpoint() + INPUTS + id + OUTPUT, PUT, exception);
        }
    }

    public HashMap<String, Object> authenticateIfHaveNot() {
        if (session == null) {
            session = authenticationClient.authenticate();
        }

        HashMap<String, Object> authHeader = new HashMap<>();
        authHeader.put(AUTHORIZATION_HEADER, BEARER + session.getToken());

        return authHeader;
    }

    @Nullable
    private <T> T exchange(String queryParameters, String body, HTTPMethod method, Class<T> type) throws JsonParseException, ApiCallException {
        return exchange(queryParameters, body, method, type, new HashMap<>());
    }

    @Nullable
    private <T> T exchange(String queryParameters, String body, HTTPMethod method, Class<T> type, Map<String, Object> headers) throws JsonParseException, ApiCallException {
        HashMap<String, Object> authHeader = authenticateIfHaveNot();
        authHeader.putAll(headers);

        String result = template.exchange(session.getEndpoint() + queryParameters, new RequestInfo()
                .setBody(body)
                .setMediaType(MediaType.JSON)
                .setHeaders(authHeader)
                .setMethod(method)
        );

        return parseJson(type, result);
    }

    @Nullable
    private <T> T parseJson(Class<T> type, String result) throws JsonParseException {
        try {
            if (type != Void.class) {
                return mapper.readValue(result, type);
            }
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse response from server" + result, e);
        }

        return null;
    }

    @Nullable
    private <T> String createJson(T result) throws JsonParseException {
        try {
            return mapper.writeValueAsString(result);

        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json: " + result, e);
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTemplate(HttpClient template) {
        this.template = template;
    }
}
