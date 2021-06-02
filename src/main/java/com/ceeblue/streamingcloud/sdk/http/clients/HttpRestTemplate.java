package com.ceeblue.streamingcloud.sdk.http.clients;

import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.RequestInfo;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * HttpClient implementation with RestTemplate inside.
 */
public class HttpRestTemplate implements HttpClient {
    private final RestTemplate template;

    /**
     * Rest template have to handle 4xx and 5xx error and @throw ApiCallException
     */
    public HttpRestTemplate(RestTemplate template) {
        this.template = template;
    }

    @Override
    public byte[] exchange(String uri, RequestInfo payload) throws ApiCallException {
        try {
            HttpEntity<String> entity = processPayload(payload);

            return template.exchange(uri, HttpMethod.valueOf(payload.getMethod().name()), entity, byte[].class).getBody();
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
