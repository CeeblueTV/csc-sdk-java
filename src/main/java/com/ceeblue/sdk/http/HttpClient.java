package com.ceeblue.sdk.http;

import com.ceeblue.sdk.utils.ApiCallException;

public interface HttpClient {
    String exchange(String uri, RequestInfo payload) throws ApiCallException;
}
