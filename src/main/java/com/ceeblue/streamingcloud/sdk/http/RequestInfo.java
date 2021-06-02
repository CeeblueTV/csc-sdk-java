package com.ceeblue.streamingcloud.sdk.http;

import java.util.HashMap;
import java.util.Map;

public class RequestInfo {

    private HTTPMethod method;

    private String body = "";

    private Map <String, Object> headers;

    private MediaType mediaType;

    public RequestInfo(HTTPMethod method, String body, Map <String, Object> headers, MediaType mediaType) {
        this.method = method;
        this.body = body;
        this.headers = headers;
        this.mediaType = mediaType;
    }

    public RequestInfo() {
        headers = new HashMap <>();
    }

    public String getBody() {
        return body;
    }

    public RequestInfo setBody(String body) {
        this.body = body;
        return this;
    }

    public Map <String, Object> getHeaders() {
        return headers;
    }

    public RequestInfo setHeaders(Map <String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public RequestInfo setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public RequestInfo setMethod(HTTPMethod method) {
        this.method = method;
        return this;
    }

}
