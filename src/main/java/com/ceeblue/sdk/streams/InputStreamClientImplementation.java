package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.authentiffication.AuthenticationService;
import com.ceeblue.sdk.authentiffication.Session;
import com.ceeblue.sdk.http.template.HTTPMethod;
import com.ceeblue.sdk.http.template.HttpTemplate;
import com.ceeblue.sdk.http.template.MediaType;
import com.ceeblue.sdk.http.template.RequestInfo;
import com.ceeblue.sdk.streams.models.*;
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
import static com.ceeblue.sdk.http.template.HTTPMethod.*;
import static com.ceeblue.sdk.streams.models.CodecName.AAC;
import static com.ceeblue.sdk.streams.models.CodecName.H264;
import static com.ceeblue.sdk.streams.models.TrackType.Audio;
import static com.ceeblue.sdk.streams.models.TrackType.Video;

@Service
public class InputStreamClientImplementation implements InputStreamClient {

    public static final String INPUTS = "/inputs/";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String OUTPUT = "/output/";
    private final AuthenticationService authenticationService;
    private HttpTemplate template;
    private Session session;

    public InputStreamClientImplementation(AuthenticationService authenticationService, HttpTemplate template) {
        this.authenticationService = authenticationService;
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

            System.out.println("Result of delete:\n" + getInput(createdStream.getId()));
        } catch (RuntimeException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public CreatedStream createStream(Stream stream) {
        try {

            String json;
            try {
                json = mapper.writeValueAsString(stream);
            } catch (JsonProcessingException e) {
                throw new JsonParseException("Can't parse passed stream for creation. Stream: " + stream, e);
            }

            return exchange(INPUTS, json, POST, CreatedStream.class);

        } catch (RuntimeException exception) {
            throw new ClientException("Can't create stream", session + INPUTS, POST, exception);
        }
    }

    @Override
    public List<CreatedStream> getInputs() {
        try {

            return Arrays.stream(exchange( INPUTS, "", GET, CreatedStream[].class))
                    .collect(Collectors.toList());

        } catch (RuntimeException exception) {
            throw new ClientException("Can't get stream", session + INPUTS, GET, exception);

        }

    }

    @Override
    public CreatedStream getInput(String id) {
        try {
            return exchange( INPUTS + id, "", GET, CreatedStream.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't get stream", session + INPUTS + id, GET, exception);
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
            try {
                String body = mapper.writeValueAsString(updated);

                return exchange( INPUTS + id, body, PUT, CreatedStream.class);
            } catch (JsonProcessingException e) {
                throw new JsonParseException("Can't create json from passed parameters. Params: " + updated, e);
            }

        } catch (RuntimeException exception) {
            throw new ClientException("Can't update stream", INPUTS + id + INPUTS + id, PUT, exception);
        }
    }

    @Override
    public void deleteInput(String id) {
        try {
            exchange( INPUTS + id, "", DELETE, String.class);

        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", session + INPUTS + id, DELETE, exception);
        }
    }

    @Override
    public Output getOutput(String id) {
        try {
            return exchange( INPUTS + id + OUTPUT, "", GET, Output.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't get output", session+ INPUTS + id + OUTPUT, GET, exception);
        }
    }

    @Override
    public Output updateOutput(String id, Output output) {
        try {
            String body = mapper.writeValueAsString(output);
            return exchange( INPUTS + id + OUTPUT, body, PUT, Output.class);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + output, e);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't update output", session.getEndpoint() + INPUTS + id + OUTPUT, PUT, exception);
        }
    }

    public HashMap<String, Object> authenticateIfHaveNot() {
        if (session == null) {
          session =  authenticationService.authenticate();
        }

        HashMap<String, Object> authHeader = new HashMap<>();
        authHeader.put(AUTHORIZATION_HEADER, BEARER + session.getToken());

        return authHeader;
    }

    @Nullable
    private <T> T exchange(String queryParameters, String body, HTTPMethod method, Class<T> type) throws JsonParseException {
        return exchange(queryParameters, body, method, type, new HashMap<>());
    }

    @Nullable
    private <T> T exchange(String queryParameters, String body, HTTPMethod method, Class<T> type, Map<String, Object> headers) throws JsonParseException {
        HashMap<String, Object> authHeader = authenticateIfHaveNot();
        authHeader.putAll(headers);

        String result = template.exchange(session.getEndpoint() + queryParameters, new RequestInfo()
                .setBody(body)
                .setMediaType(MediaType.JSON)
                .setHeaders(authHeader)
                .setMethod(method)
        );

        try {
            if (result != null) {
                return mapper.readValue(result, type);
            } else {
                return null; // if server return nothing
            }
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse response from server" + result, e);
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTemplate(HttpTemplate template) {
        this.template = template;
    }
}
