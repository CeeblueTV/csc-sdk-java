package com.ceeblue.sdk.authentiffication;

import com.ceeblue.sdk.authentiffication.utils.AuthorizationException;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.http.RequestInfo;
import com.ceeblue.sdk.http.template.utils.HTTPMethod;
import com.ceeblue.sdk.http.template.utils.MediaType;
import com.ceeblue.sdk.settings.Credential;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.*;

@Component
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
            endpoint = DEFAULT_ENDPOINT;
        }
        session.setEndpoint(endpoint);
    }

    @Autowired
    public AuthenticationClientImplementation(Credential credentials, HttpClient template) {
        this.credential = credentials;
        this.template = template;
        session.setEndpoint(DEFAULT_ENDPOINT);
    }

    @Override
    public Session authenticate() {
        if (session.getToken() != null) {
            return session;
        }

        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };

        String body = getBody();

        byte[] result = template.exchange(session.getEndpoint() + LOGIN, new RequestInfo()
                .setBody(body)
                .setHeaders(new HashMap<>())
                .setMethod(HTTPMethod.POST)
                .setMediaType(MediaType.JSON));
        try {
            return session.setToken(mapper.readValue(new String(result), typeRef).get(TOKEN));
        } catch (JsonProcessingException e) {
            throw new AuthorizationException("Invalid response from Server: " + result);
        }
    }

    private String getBody() {
        Map<String, Object> payload = new HashMap<>();
        payload.put(USERNAME, credential.getUsername());
        payload.put(PASSWORD, credential.getPassword());

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse passed stream for creation. Stream: " + payload, e);
        }
    }
}
