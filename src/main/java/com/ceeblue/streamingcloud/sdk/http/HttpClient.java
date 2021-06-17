package com.ceeblue.streamingcloud.sdk.http;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;

/**
 * Synchronous client to perform HTTP requests, exposing a simple,
 * template method API over underlying HTTP client libraries such as the  RestTemplate, OkHttp3, JDK HttpURLConnection, etc.
 * <p>
 * The HttpClient offers generalized exchange method for all HTTP methods.
 * Implementation must handle timeout, 5xx and 4xx errors and throw {@link ApiCallException}.
 */
public interface HttpClient {

    /**
     * method to send request
     *
     * @param uri     URI of API call
     * @param payload request info e.g. http method, body, headers etc
     * @return Return result from server
     * @throws ApiCallException In case when couldn't perform request. Some error on server were occurred, timeout or
     *                          authentication problem
     */
    ResponseInfo exchange(String uri, RequestInfo payload) throws ApiCallException;

}
