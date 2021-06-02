package com.ceeblue.streamingcloud.sdk.authentiffication;

import com.ceeblue.streamingcloud.sdk.authentiffication.utils.AuthenticationConstants;
import com.ceeblue.streamingcloud.sdk.authentiffication.utils.AuthorizationException;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.RequestInfo;
import com.ceeblue.streamingcloud.sdk.http.template.utils.HTTPMethod;
import com.ceeblue.streamingcloud.sdk.http.template.utils.MediaType;
import com.ceeblue.streamingcloud.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationClientImplementation implements AuthenticationClient {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static final String TOKEN = "token";

    final Credential credential;

    private final Session session = new Session();

    private final HttpClient template;

    public AuthenticationClientImplementation(Credential credentials, HttpClient template, String endpoint) {
        this.credential = credentials;
        this.template = template;
        if (endpoint == null) {
            endpoint = AuthenticationConstants.DEFAULT_ENDPOINT;
        }
        session.setEndpoint(endpoint);
    }

    public AuthenticationClientImplementation(Credential credentials, HttpClient template) {
        this.credential = credentials;
        this.template = template;
        session.setEndpoint(AuthenticationConstants.DEFAULT_ENDPOINT);
    }

    @Override
    public Session authenticate() {
        if (session.getToken() != null) {
            return session;
        }

        TypeReference <HashMap <String, String>> typeRef = new TypeReference <HashMap <String, String>>() {
        };

        String body = getBody();

        byte[] result = template.exchange(session.getEndpoint() + AuthenticationConstants.LOGIN, new RequestInfo()
                .setBody(body)
                .setHeaders(new HashMap <>())
                .setMethod(HTTPMethod.POST)
                .setMediaType(MediaType.JSON));
        try {
            return session.setToken(mapper.readValue(new String(result), typeRef).get(TOKEN));
        } catch (JsonProcessingException e) {
            throw new AuthorizationException("Invalid response from Server: " + result);
        }
    }

    private String getBody() {
        Map <String, Object> payload = new HashMap <>();
        payload.put(AuthenticationConstants.USERNAME, credential.getUsername());
        payload.put(AuthenticationConstants.PASSWORD, credential.getPassword());

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse passed stream for creation. Stream: " + payload, e);
        }
    }

}
