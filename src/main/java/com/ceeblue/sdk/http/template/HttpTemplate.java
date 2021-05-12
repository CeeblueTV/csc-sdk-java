package com.ceeblue.sdk.http.template;

import com.ceeblue.sdk.utils.JsonParseException;

import java.util.Map;

public interface HttpTemplate {

    void authorize(String token);

    <T> T get(Class<T> resultClass, String uri, String body, Map<String, String> headers) throws JsonParseException;

    <T> T get(Class<T> resultClass, String uri);

    <T> T post(Class<T> resultClass, String uri, String body, Map<String, String> headers);

    <T> T post(Class<T> resultClass, String uri);

    <T> T put(Class<T> resultClass, String uri, String body, Map<String, String> headers);

    <T> T put(Class<T> resultClass, String uri);

    <T> T delete(Class<T> resultClass, String uri, String body, Map<String, String> headers);

    <T> T delete(Class<T> resultClass, String uri);
}
