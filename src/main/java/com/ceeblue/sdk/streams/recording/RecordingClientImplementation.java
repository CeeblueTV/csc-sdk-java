package com.ceeblue.sdk.streams.recording;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.ApiClient;
import com.ceeblue.sdk.streams.recording.models.Recording;
import com.ceeblue.sdk.streams.recording.models.created.CreatedRecording;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

public class RecordingClientImplementation extends ApiClient implements RecordingClient {

    private static final String RECORDINGS = "/recordings/";
    private static final String STREAM = "/stream/";
    private static final String STOP = "/stop";

    protected RecordingClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    protected RecordingClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedRecording createRecording(Recording recording) {
        try {
            String json = createJson(recording);

            return exchange(RECORDINGS, json, POST, CreatedRecording.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't add recording: " + recording, session + RECORDINGS, POST, exception);
        }
    }

    @Override
    public CreatedRecording getRecording(String recordingId) {
        try {
            return exchange(RECORDINGS + recordingId, "", GET, CreatedRecording.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get recordings: ", session + RECORDINGS + recordingId, GET, exception);
        }
    }

    @Override
    public List<CreatedRecording> getRecordingByStreamId(String streamId) {
        try {
            CreatedRecording[] result = exchange(RECORDINGS + STREAM + streamId, "", GET, CreatedRecording[].class);

            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get recordings for Stream: " + streamId, RECORDINGS + STREAM + streamId, GET, exception);
        }

        throw new ClientException("Can't get stream recordings", session + RECORDINGS, GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public List<CreatedRecording> getRecordings() {
        try {

            CreatedRecording[] result = exchange(RECORDINGS, "", GET, CreatedRecording[].class);
            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get recordings", session + RECORDINGS, GET, exception);
        }

        throw new ClientException("Can't get recordings", session + RECORDINGS, GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public void stopRecording(String recordingId) {
        try {
            exchange(RECORDINGS + recordingId + STOP, "", PUT, Void.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't stop recording", RECORDINGS + recordingId + STOP, PUT, exception);
        }
    }

    @Override
    public void deleteRecording(String recordingId) {
        try {
            exchange(RECORDINGS + recordingId, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete recording", RECORDINGS + recordingId, DELETE, exception);
        }
    }
}
