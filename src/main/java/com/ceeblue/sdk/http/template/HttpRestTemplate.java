package com.ceeblue.sdk.http.template;

import com.ceeblue.sdk.authentiffication.utils.RestTemplateResponseErrorHandler;
import com.ceeblue.sdk.utils.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.AUTHORIZATION_HEADER;
import static com.ceeblue.sdk.authentiffication.utils.AuthenticationConstants.BEARER;

@Component
public class HttpRestTemplate implements HttpTemplate {
    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate template;
    String token;

    public HttpRestTemplate(RestTemplateBuilder template) {
        this.template = template.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    @Override
    public void authorize(String token) {
        this.token = token;
    }

    @Override
    public <T> T get(Class<T> resultClass, String uri, String body, Map<String, String> headers) throws JsonParseException {
        try {
            HttpEntity<String> entity = getPayload(body, headers);

            return sendRequest(HttpMethod.GET, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + body, e);
        }
    }

    @Override
    public <T> T get(Class<T> resultClass, String uri) {
        try {
            HttpEntity<String> entity = getPayload(null, new HashMap<>());

            return sendRequest(HttpMethod.GET, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters", e);
        }
    }

    @Override
    public <T> T post(Class<T> resultClass, String uri, String body, Map<String, String> headers) {
        try {
            HttpEntity<String> entity = getPayload(body, headers);

            return sendRequest(HttpMethod.POST, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + body, e);
        }
    }

    @Override
    public <T> T post(Class<T> resultClass, String uri) {
        try {
            HttpEntity<String> entity = getPayload(null, new HashMap<>());

            return sendRequest(HttpMethod.POST, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters", e);
        }
    }

    @Override
    public <T> T put(Class<T> resultClass, String uri, String body, Map<String, String> headers) {
        try {
            HttpEntity<String> entity = getPayload(body, headers);

            return sendRequest(HttpMethod.PUT, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + body, e);
        }
    }

    @Override
    public <T> T put(Class<T> resultClass, String uri) {
        try {
            HttpEntity<String> entity = getPayload(null, new HashMap<>());

            return sendRequest(HttpMethod.PUT, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters", e);
        }
    }

    @Override
    public <T> T delete(Class<T> resultClass, String uri, String body, Map<String, String> headers) {
        try {
            HttpEntity<String> entity = getPayload(body, headers);

            return sendRequest(HttpMethod.DELETE, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters. Params: " + body, e);
        }
    }

    @Override
    public <T> T delete(Class<T> resultClass, String uri) {
        try {
            HttpEntity<String> entity = getPayload("", new HashMap<>());

            return sendRequest(HttpMethod.DELETE, resultClass, uri, entity);
        } catch (JsonProcessingException e) {
            throw new JsonParseException("Can't create json from passed parameters", e);
        }
    }


    private HttpEntity<String> getPayload(String body, Map<String, String> headers) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (token != null) {
            httpHeaders.set(AUTHORIZATION_HEADER, BEARER + token);
        }

        headers.forEach(httpHeaders::set);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(body, httpHeaders);
    }

    private <T> T sendRequest(HttpMethod method, Class<T> resultClass, String uri, HttpEntity<String> entity) {
        return template.exchange(uri, method, entity, resultClass).getBody();
    }
}
