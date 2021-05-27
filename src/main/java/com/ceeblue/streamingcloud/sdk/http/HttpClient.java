package com.ceeblue.streamingcloud.sdk.http;

import com.ceeblue.streamingcloud.sdk.utils.ApiCallException;

/**
 * client for http request. Implementation have to handle timeout, 5xx and 4xx errors and throw {@link ApiCallException}.
 */
public interface HttpClient {
    /**
     * method to send request
     *
     * @param uri     URI of api call
     * @param payload request data e.g headers, body etc
     * @return Return result from server in byte[]
     * @throws ApiCallException if timeout, some error on server were occurred, or
     *                          Auth header from payload were missing or hasn't access to that api
     */
    byte[] exchange(String uri, RequestInfo payload) throws ApiCallException;
}
