package com.ceeblue.sdk.authentiffication;

import com.ceeblue.sdk.authentiffication.utils.AuthorizationException;
import com.ceeblue.sdk.http.template.HttpTemplate;
import com.ceeblue.sdk.settings.Settings;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.*;

@Component
public class AuthenticationServiceImplementation implements AuthenticationService {

    public static final ObjectMapper mapper = new ObjectMapper();

    final Settings settings;

    private final HttpTemplate template;

    public AuthenticationServiceImplementation(Settings credentials, HttpTemplate template) {
        this.settings = credentials;
        this.template = template;
    }

    @Override
    public String authenticate() {
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };

        String body = getBody();

        String token = template.post(String.class, settings.getApi() + LOGIN, body, new HashMap<>());

        try {
            settings.setToken(mapper.readValue(token, typeRef).get("token"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AuthorizationException("Invalid response From Server");
        }

        return settings.getToken();
    }

    private String getBody() {
        Map<String, Object> payload = new HashMap<>();
        payload.put(USERNAME, settings.getUsername());
        payload.put(PASSWORD, settings.getPassword());

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't parse passed stream for creation. Stream: " + payload, e);
        }
    }

    @Override
    public String getOrCreateToken() {
        if (!StringUtils.hasText(settings.getToken())) {
            authenticate();
        }

        return settings.getToken();
    }
}
