package com.ceeblue.sdk.authentiffication;

import org.springframework.boot.web.client.RestTemplateBuilder;

public interface AuthenticationService {

    String authenticate();

    String getOrCreateToken();

    RestTemplateBuilder getAuthenticatedBuilder();
}
