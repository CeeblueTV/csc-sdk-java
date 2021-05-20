package com.ceeblue.sdk.streams.push;

import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.push.models.push.CreatedPush;
import com.ceeblue.sdk.streams.push.models.push.Push;
import com.ceeblue.sdk.utils.ClientException;

import java.util.List;

public interface StreamPushClient {

    CreatedPush createPush(Push push) throws ClientException;

    CreatedPush getStreamPush(String id) throws ClientException;

    List<String> retrieveStreamPush(String id) throws ClientException;

    void setTemplate(HttpClient template);

    void deleteStreamPush(String id);
}
