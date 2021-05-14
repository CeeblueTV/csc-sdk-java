package com.ceeblue.sdk.http.template;

import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

@Component
public class HttpRestTemplate implements HttpTemplate {
    private final RestTemplate template;

    public HttpRestTemplate(RestTemplateBuilder template) {
        this.template = template.errorHandler(new RestTemplateResponseErrorHandler())
                .setConnectTimeout(Duration.of(10000, MILLIS))
                .setReadTimeout(Duration.of(10000, MILLIS)).build();
    }

    @Override
    public String exchange(String uri, RequestInfo payload) {
        try {
            HttpEntity<String> entity = processPayload(payload);

            return template.exchange(uri, HttpMethod.valueOf(payload.getMethod().name()), entity, String.class).getBody();
        } catch (ResourceAccessException exception) {
            throw new ApiCallException("Timeout", -1, exception.getMessage());
        }
    }

    private HttpEntity<String> processPayload(RequestInfo payload) {
        HttpHeaders httpHeaders = new HttpHeaders();

        payload.getHeaders().forEach((headerName, headerValue) -> httpHeaders.set(headerName, headerValue.toString()));

        httpHeaders.setContentType(MediaType.valueOf(payload.getMediaType().getType()));

        return new HttpEntity<>(payload.getBody(), httpHeaders);
    }

}
