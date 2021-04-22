package com.ceeblue.sdk.authentiffication;

import com.ceeblue.sdk.authentiffication.utils.AuthorizationException;
import com.ceeblue.sdk.authentiffication.utils.RestTemplateResponseErrorHandler;
import com.ceeblue.sdk.settings.Settings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.*;

@Component
public class AuthenticationServiceImplementation implements AuthenticationService {

    public static final ObjectMapper mapper = new ObjectMapper();

    final Settings settings;

    final RestTemplateBuilder builder;

    public AuthenticationServiceImplementation(Settings credentials, RestTemplateBuilder builder) {
        this.settings = credentials;
        this.builder = builder;
    }

    @Override
    public String authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = credentialsToJson();

        String token = authorize(headers, json);

        settings.setToken(token);

        return settings.getToken();
    }

    private String authorize(HttpHeaders headers, String json) {
        TypeReference<HashMap<String, String>> typeRef
                = new TypeReference<HashMap<String, String>>() {
        };

        String token = builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build()
                .postForObject(settings.getApi() + LOGIN, new HttpEntity<>(json, headers), String.class);

        try {
            return mapper.readValue(token, typeRef).get("token");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AuthorizationException("Invalid response From Server");
        }
    }

    private String credentialsToJson() {
        Map<String, String> payload = new HashMap<>();
        payload.put(USERNAME, settings.getUsername());
        payload.put(PASSWORD, settings.getPassword());

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AuthorizationException("Unpredictable behavior. Please Ping someone from R&D");
        }
    }

    @Override
    public HttpEntity<String> getAuthenticationHeader() {
        if (!StringUtils.hasText(settings.getToken())) {
            authenticate();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTHORIZATION_HEADER, BEARER + settings.getToken());

        return new HttpEntity<>("", headers);
    }

    @Override
    public String getToken() {
        if (!StringUtils.hasText(settings.getToken())) {
            authenticate();
        }

        return settings.getToken();
    }

    @Override
    public RestTemplateBuilder getBuilderWithToken() {
        if (!StringUtils.hasText(settings.getToken())) {
            authenticate();
        }

        return builder.defaultHeader(AUTHORIZATION_HEADER, BEARER + settings.getToken());
    }
}
