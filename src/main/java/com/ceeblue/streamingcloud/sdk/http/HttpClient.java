package com.ceeblue.streamingcloud.sdk.http;

import com.ceeblue.streamingcloud.sdk.utils.ApiCallException;

public interface HttpClient {
    byte[] exchange(String uri, RequestInfo payload) throws ApiCallException;
}
