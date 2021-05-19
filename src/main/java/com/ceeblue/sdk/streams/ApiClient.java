package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.authentiffication.Session;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.http.RequestInfo;
import com.ceeblue.sdk.http.template.utils.HTTPMethod;
import com.ceeblue.sdk.http.template.utils.MediaType;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.*;

public abstract class ApiClient {

    public static final ObjectMapper mapper = new ObjectMapper();
    private final AuthenticationClient authenticationClient;
    protected HttpClient template;
    protected Session session;

    protected ApiClient(AuthenticationClient authenticationClient, HttpClient template) {
        this.authenticationClient = authenticationClient;
        this.template = template;
        session = new Session().setEndpoint(DEFAULT_ENDPOINT);
    }

    protected ApiClient(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        this.authenticationClient = authenticationClient;
        this.template = template;
        session = new Session().setEndpoint(endpoint);
    }

    @Nullable
    protected <T> T exchange(String parts, String body, HTTPMethod method, Class<T> type) throws JsonParseException, ApiCallException {
        return exchange(parts, body, method, type, new HashMap<>());
    }

    @Nullable
    protected <T> T exchange(String parts, String body, HTTPMethod method, Class<T> type, Map<String, Object> headers) throws JsonParseException, ApiCallException {
        HashMap<String, Object> authHeader = authenticateIfHaveNot();
        authHeader.putAll(headers);

        String result = template.exchange(session.getEndpoint() + parts, new RequestInfo()
                .setBody(body)
                .setMediaType(MediaType.JSON)
                .setHeaders(authHeader)
                .setMethod(method)
        );

        return parseJson(type, result);
    }

    @Nullable
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

    @Nullable
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
