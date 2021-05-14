package com.ceeblue.sdk.http.template;

public interface HttpTemplate {
    String exchange(String uri, RequestInfo payload);
}
