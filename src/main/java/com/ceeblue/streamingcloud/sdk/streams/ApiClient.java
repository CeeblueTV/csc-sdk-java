package com.ceeblue.streamingcloud.sdk.streams;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.authentiffication.Session;
import com.ceeblue.streamingcloud.sdk.http.*;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationConstants.DEFAULT_ENDPOINT;

/**
 * Helper for all API clients. Contains authorization and utils methods for working with json and authentication
 */
public abstract class ApiClient {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER = "Bearer ";

    private final ObjectMapper mapper = new ObjectMapper();

    private final AuthenticationClient authenticationClient;

    protected HttpClient template;

    protected Session session;

    protected ApiClient(AuthenticationClient authenticationClient, HttpClient template) {
        this.authenticationClient = authenticationClient;
        this.template = template;
        session = new Session().setEndpoint(DEFAULT_ENDPOINT);
    }

    protected ApiClient(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        this(authenticationClient, template);

        session = new Session().setEndpoint(endpoint);
    }

    protected <T> T exchange(String parts, String body, HTTPMethod method, Class<T> type) throws JsonParseException, ApiCallException {
        return exchange(parts, body, method, type, new HashMap<>(), MediaType.JSON);
    }

    protected <T> T exchange(String parts, String body, HTTPMethod method, Class<T> type, Map<String, Object> headers, MediaType mediaType) throws JsonParseException, ApiCallException {
        HashMap<String, Object> authHeader = authenticateIfHaveNot();
        authHeader.putAll(headers);

        ResponseInfo result = template.exchange(session.getEndpoint() + parts, new RequestInfo()
                .setBody(body)
                .setMediaType(mediaType)
                .setHeaders(authHeader)
                .setMethod(method)
        );

        return processRequestResult(type, result.getBody());
    }

    protected ResponseInfo exchange(String parts, String body, HTTPMethod method, Map<String, Object> headers, MediaType mediaType) throws JsonParseException, ApiCallException {
        HashMap<String, Object> authHeader = authenticateIfHaveNot();
        authHeader.putAll(headers);

        return template.exchange(session.getEndpoint() + parts, new RequestInfo()
                .setBody(body)
                .setMediaType(mediaType)
                .setHeaders(authHeader)
                .setMethod(method)
        );
    }

    @SuppressWarnings("unchecked")
    private <T> T processRequestResult(Class<T> type, byte[] result) {
        if (type != byte[].class) {
            String json = null;
            if (type != Void.class) {
                json = new String(result);
            }
            return parseJson(type, json);
        }

        return (T) result;
    }

    protected <T> T parseJson(Class<T> type, String result) throws JsonParseException {
        try {
            if (type != Void.class) {
                return mapper.readValue(result, type);
            }
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse response from server" + result, e);
        }

        return null;
    }

    protected <T> String createJson(T result) throws JsonParseException {
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json: " + result, e);
        }
    }

    public HashMap<String, Object> authenticateIfHaveNot() {
        if (session == null || session.getToken() == null) {
            session = authenticationClient.authenticate();
        }

        HashMap<String, Object> authHeader = new HashMap<>();
        authHeader.put(AUTHORIZATION_HEADER, BEARER + session.getToken());

        return authHeader;
    }

    public void setTemplate(HttpClient template) {
        this.template = template;
    }
}
