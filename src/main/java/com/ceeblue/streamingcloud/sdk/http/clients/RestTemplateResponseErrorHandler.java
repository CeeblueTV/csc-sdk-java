package com.ceeblue.streamingcloud.sdk.http.clients;


import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
                httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        String body = "";
        if (httpResponse.getStatusCode().series().value() == HttpStatus.Series.CLIENT_ERROR.value()) {
            if (httpResponse.getBody() != null) {

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
                    body = reader.lines().collect(Collectors.joining(""));

                }
            }

            throw new ApiCallException("Can't evaluate request", httpResponse.getStatusCode().value(), body);
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            if (httpResponse.getBody() != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
                    body = reader.lines().collect(Collectors.joining(""));
                }
            }

            throw new ApiCallException("Internal error on remote server", httpResponse.getStatusCode().value(), body);
        }
    }
}
