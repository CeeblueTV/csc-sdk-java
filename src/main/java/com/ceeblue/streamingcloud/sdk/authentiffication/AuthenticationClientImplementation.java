package com.ceeblue.streamingcloud.sdk.authentiffication;

import com.ceeblue.streamingcloud.sdk.http.HTTPMethod;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.MediaType;
import com.ceeblue.streamingcloud.sdk.http.RequestInfo;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.streamingcloud.sdk.authentiffication.utils.AuthenticationConstants.DEFAULT_ENDPOINT;

public class AuthenticationClientImplementation implements AuthenticationClient {

    private static final String TOKEN = "token";

    private static final String LOGIN = "/login";

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    private final Credentials credentials;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Session session = new Session();

    private final HttpClient template;

    public AuthenticationClientImplementation(Credentials credentials, HttpClient template, String endpoint) {
        this(credentials, template);

        if (endpoint != null) {
            session.setEndpoint(endpoint);
        }
    }

    public AuthenticationClientImplementation(Credentials credentials, HttpClient template) {
        this.credentials = credentials;
        this.template = template;
        session.setEndpoint(DEFAULT_ENDPOINT);
    }

    @Override
    public Session authenticate() {
        if (session.getToken() != null) {
            return session;
        }

        TypeReference <HashMap <String, String>> typeRef = new TypeReference <HashMap <String, String>>() {
        };

        String body = getBody();

        byte[] result = template.exchange(session.getEndpoint() + LOGIN, new RequestInfo()
                .setBody(body)
                .setHeaders(new HashMap <>())
                .setMethod(HTTPMethod.POST)
                .setMediaType(MediaType.JSON));
        try {
            return session.setToken(mapper.readValue(new String(result), typeRef).get(TOKEN));
        } catch (JsonProcessingException e) {
            throw new AuthorizationException("Invalid response from Server: " + new String(result));
        }
    }

    private String getBody() {
        Map <String, Object> payload = new HashMap <>();
        payload.put(USERNAME, credentials.getUsername());
        payload.put(PASSWORD, credentials.getPassword());

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse passed stream for creation. Stream: " + payload, e);
        }
    }
}
