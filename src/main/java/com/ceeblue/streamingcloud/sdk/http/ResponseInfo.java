package com.ceeblue.streamingcloud.sdk.http;

import java.util.Arrays;
import java.util.Map;

public class ResponseInfo {

    private byte[] body;

    private Map <String, String> headers;

    public ResponseInfo(byte[] body, Map <String, String> headers) {
        this.body = body;
        this.headers = headers;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Map <String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map <String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                (body != null ? "body=" + Arrays.toString(body) : "") +
                (body != null ? ", headers=" + headers : "") +
                "}";
    }
}
