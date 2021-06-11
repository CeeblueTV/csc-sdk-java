package com.ceeblue.streamingcloud.sdk.streams.push;

import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.Push;
import com.ceeblue.streamingcloud.sdk.streams.push.models.push.PushEndpoint;

import java.util.List;

/**
 * Client for push manipulation
 */
public interface StreamPushClient {

    /**
     * Create output for {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint}
     *
     * @param push push settings that will be applied
     * @return created push
     */
    PushEndpoint createPush(Push push) throws ClientException;

    /**
     * Fetch push by it's id
     *
     * @param id id of push {@see PushEndpoint}
     * @return output list
     */
    PushEndpoint getStreamPush(String id) throws ClientException;

    /**
     * Fetch push id's of input stream
     *
     * @param id input stream id {@see com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint}
     *
     * @return list of push id {@see PushEndpoint}
     */
    List <String> retrieveStreamPush(String id) throws ClientException;

    /**
     * Delete push
     *
     * @param id push id {@see PushEndpoint}
     */
    void deleteStreamPush(String id);

}
