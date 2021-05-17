package com.ceeblue.sdk.streams.push;

import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.push.models.CreatedPush;
import com.ceeblue.sdk.streams.push.models.StreamPush;
import com.ceeblue.sdk.utils.ClientException;

import java.util.List;

public interface StreamPushClient {

    CreatedPush addPush(StreamPush streamPush) throws ClientException;

    CreatedPush getStreamPush(String id) throws ClientException;

    void deleteStreamPush(String id);

    List<String> retrieveStreamPush(String id) throws ClientException;

    void setTemplate(HttpClient template);
}
