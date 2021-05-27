package com.ceeblue.streamingcloud.sdk.streams.push;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.CreatedPush;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.Push;
import com.ceeblue.streamingcloud.sdk.utils.ApiCallException;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;
import com.ceeblue.streamingcloud.sdk.utils.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.template.utils.HTTPMethod.*;

public class ApiPushClientImplementation extends ApiClient implements StreamPushClient {

    private static final String PUSHES = "/pushes/";
    private static final String STREAM = "/stream/";

    public ApiPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public ApiPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedPush createPush(Push push) {
        try {
            String json = createJson(push);

            return exchange(PUSHES, json, POST, CreatedPush.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create stream push: " + push, session + PUSHES, POST, exception);
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

    @Override
    public void deleteStreamPush(String id) {
        try {
            exchange(PUSHES + id, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream push", session + PUSHES + id, DELETE, exception);
        }
    }
}
