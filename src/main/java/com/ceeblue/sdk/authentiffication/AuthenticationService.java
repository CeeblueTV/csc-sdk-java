package com.ceeblue.sdk.authentiffication;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;

public interface AuthenticationService {

    String authenticate();

    HttpEntity<String> getAuthenticationHeader() throws JsonProcessingException;

    String getToken() throws JsonProcessingException;

    RestTemplateBuilder getBuilderWithToken() throws JsonProcessingException;
}
