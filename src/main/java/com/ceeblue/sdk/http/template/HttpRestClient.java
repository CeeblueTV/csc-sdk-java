package com.ceeblue.sdk.http.template;

import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.http.RequestInfo;
import com.ceeblue.sdk.utils.ApiCallException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRestClient implements HttpClient {
    private final RestTemplate template;

    public HttpRestClient(@Qualifier("okHttpRestTemplate") RestTemplate template) {
        this.template =  template;
    }

    @Override
    public String exchange(String uri, RequestInfo payload) throws ApiCallException {
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
