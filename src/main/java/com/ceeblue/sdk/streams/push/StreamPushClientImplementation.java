package com.ceeblue.sdk.streams.push;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.StreamClient;
import com.ceeblue.sdk.streams.push.models.CreatedPush;
import com.ceeblue.sdk.streams.push.models.StreamPush;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

@Service
public class StreamPushClientImplementation extends StreamClient implements StreamPushClient {

    private static final String PUSHES = "/pushes/";
    private static final String STREAM = "/stream/";

    @Autowired
    public StreamPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public StreamPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedPush addPush(StreamPush streamPush) {
        try {
            String json = createJson(streamPush);

            return exchange(PUSHES, json, POST, CreatedPush.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create stream push: " + streamPush, session + PUSHES, POST, exception);
        }
    }

    @Override
    public CreatedPush getStreamPush(String id) {
        try {
            return exchange(PUSHES + id, "", GET, CreatedPush.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream push", session + PUSHES + id, GET, exception);
        }
    }

    @Override
    public void deleteStreamPush(String id) {
        try {
            exchange(PUSHES + id, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream push", session + PUSHES + id, DELETE, exception);
        }
    }

    @Override
    public List<String> retrieveStreamPush(String id) {
        try {
            String[] result = exchange(PUSHES + STREAM, "", GET, String[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get stream push ids. Id: " + id, session + PUSHES + STREAM, GET, exception);
        }

        throw new ClientException("Can't get stream push ids. Id: " + id, session + PUSHES + STREAM, GET, new RuntimeException("No result from server!!!"));
    }
}
