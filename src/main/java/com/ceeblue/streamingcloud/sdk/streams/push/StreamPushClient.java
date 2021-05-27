package com.ceeblue.streamingcloud.sdk.streams.push;

import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.CreatedPush;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.Push;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.util.List;

public interface StreamPushClient {

    CreatedPush createPush(Push push) throws ClientException;

    CreatedPush getStreamPush(String id) throws ClientException;

    List<String> retrieveStreamPush(String id) throws ClientException;

    void setTemplate(HttpClient template);

    void deleteStreamPush(String id);
}
