package com.ceeblue.sdk.authentiffication;

import com.ceeblue.sdk.authentiffication.utils.AuthorizationException;
import com.ceeblue.sdk.http.template.HTTPMethod;
import com.ceeblue.sdk.http.template.HttpTemplate;
import com.ceeblue.sdk.http.template.MediaType;
import com.ceeblue.sdk.http.template.RequestInfo;
import com.ceeblue.sdk.settings.Credential;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.*;

@Component
public class AuthenticationServiceImplementation implements AuthenticationService {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String TOKEN = "token";

    final Credential credential;
    private final Session session = new Session();
    private HttpTemplate template;

    public AuthenticationServiceImplementation(Credential credentials, HttpTemplate template, @Nullable String endpoint) {
        this.credential = credentials;
        this.template = template;
        if (endpoint == null) {
            endpoint = DEFAULT_ENDPOINT;
        }
        session.setEndpoint(endpoint);
    }

    @Override
    public Session authenticate() {
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };

        String body = getBody();

        String jsonToken = template.exchange(session.getEndpoint() + LOGIN, new RequestInfo()
                .setBody(body)
                .setHeaders(new HashMap<>())
                .setMethod(HTTPMethod.POST)
                .setMediaType(MediaType.JSON));
        try {
            return session.setToken(mapper.readValue(jsonToken, typeRef).get(TOKEN));
        } catch (JsonProcessingException e) {
            throw new AuthorizationException("Invalid response from Server: " + jsonToken);
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

    public void setTemplate(HttpTemplate template) {
        this.template = template;
    }
}
