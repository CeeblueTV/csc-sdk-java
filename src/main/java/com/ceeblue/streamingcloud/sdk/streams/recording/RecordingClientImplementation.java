package com.ceeblue.streamingcloud.sdk.streams.recording;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.Recording;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.created.CreatedRecording;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;

public class RecordingClientImplementation extends ApiClient implements RecordingClient {

    private static final String RECORDINGS = "/recordings/";

    private static final String STREAM = "/stream/";

    private static final String STOP = "/stop";

    public RecordingClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public RecordingClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedRecording createRecording(Recording recording) throws ClientException {
        try {
            String json = createJson(recording);

            return exchange(RECORDINGS, json, POST, CreatedRecording.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't add recording: " + recording, exception);
        } catch (ApiCallException exception) {
            throw new ClientException(exception.getServerResponse() != null ? exception.getServerResponse() : "Can't add recording: " + recording, exception);
        }
    }

    @Override
    public CreatedRecording getRecording(String recordingId) throws ClientException {
        try {
            return exchange(RECORDINGS + recordingId, "", GET, CreatedRecording.class);

        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings: ", exception);
        } catch (ApiCallException exception) {
            throw new ClientException(exception.getServerResponse() != null ? exception.getServerResponse() : "Can't get recordings: ", exception);
        }
    }

    @Override
    public List <CreatedRecording> getRecordingByStreamId(String streamId) throws ClientException {
        try {
            CreatedRecording[] result = exchange(RECORDINGS + STREAM + streamId, "", GET, CreatedRecording[].class);

            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings for Stream: " + streamId, exception);
        } catch (ApiCallException exception) {
            throw new ClientException(exception.getServerResponse() != null ? exception.getServerResponse() : "Can't get recordings for Stream: " + streamId, exception);
        }

        throw new ClientException("Can't get stream recordings", new RuntimeException("No result from server!!!"));
    }

    @Override
    public List <CreatedRecording> getRecordings() throws ClientException {
        try {

            CreatedRecording[] result = exchange(RECORDINGS, "", GET, CreatedRecording[].class);
            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings", exception);
        } catch (ApiCallException exception) {
            throw new ClientException(exception.getServerResponse() != null ? exception.getServerResponse() : "Can't get recordings", exception);
        }

        throw new ClientException("Can't get recordings", new RuntimeException("No result from server!!!"));
    }

    @Override
    public void stopRecording(String recordingId) throws ClientException {
        try {
            exchange(RECORDINGS + recordingId + STOP, "", PUT, Void.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't stop recording", exception);
        } catch (ApiCallException exception) {
            throw new ClientException(exception.getServerResponse() != null ? exception.getServerResponse() : "Can't stop recording", exception);
        }
    }

    @Override
    public void deleteRecording(String recordingId) throws ClientException {
        try {
            exchange(RECORDINGS + recordingId, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete recording", exception);
        }
    }

}
