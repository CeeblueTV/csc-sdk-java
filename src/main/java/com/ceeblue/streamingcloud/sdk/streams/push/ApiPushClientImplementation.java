package com.ceeblue.streamingcloud.sdk.streams.push;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.Push;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.PushEndpoint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StingFormatter.getServerMessage;

public class ApiPushClientImplementation extends ApiClient implements StreamPushClient {

    private static final String PUSHES = "/pushes/";

    private static final String STREAM = "stream/";

    public ApiPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public ApiPushClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public PushEndpoint createPush(Push push) {
        try {
            String json = createJson(push);

            return exchange(PUSHES, json, POST, PushEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't create stream push: " + push, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't create stream push: " + push, exception);
        }
    }

    @Override
    public PushEndpoint getStreamPush(String id) {
        try {
            return exchange(PUSHES + id, "", GET, PushEndpoint.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get stream push by id: " + id, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get stream push by id: " + id, exception);
        }
    }

    @Override
    public List <String> retrieveStreamPush(String id) {
        try {
            String[] result = exchange(PUSHES + STREAM + id, "", GET, String[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get stream push ids. Id: " + id, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get stream push ids. Id: " + id, exception);
        }

        throw new ClientException(
                "Can't get stream push ids. Id: " + id, new RuntimeException("No result from server!!!"));
    }

    @Override
    public void deleteStreamPush(String id) {
        try {
            exchange(PUSHES + id, "", DELETE, Void.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't delete stream push", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't delete stream push", exception);
        }
    }
}
