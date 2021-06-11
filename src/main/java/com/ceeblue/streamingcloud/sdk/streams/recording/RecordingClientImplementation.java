package com.ceeblue.streamingcloud.sdk.streams.recording;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.Recording;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.created.RecordingModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StingFormatter.getServerMessage;

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
    public RecordingModel createRecording(Recording recording) throws ClientException {
        try {
            String json = createJson(recording);

            return exchange(RECORDINGS, json, POST, RecordingModel.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't add recording: " + recording, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't add recording: " + recording, exception);
        }
    }

    @Override
    public RecordingModel getRecording(String recordingId) throws ClientException {
        try {
            return exchange(RECORDINGS + recordingId, "", GET, RecordingModel.class);

        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings: ", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get recordings: ", exception);
        }
    }

    @Override
    public List <RecordingModel> getRecordingByStreamId(String streamId) throws ClientException {
        try {
            RecordingModel[] result = exchange(RECORDINGS + STREAM + streamId, "", GET, RecordingModel[].class);

            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings for Stream: " + streamId, exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get recordings for Stream: " + streamId, exception);
        }

        throw new ClientException("Can't get stream recordings", new RuntimeException("No result from server!!!"));
    }

    @Override
    public List <RecordingModel> getRecordings() throws ClientException {
        try {

            RecordingModel[] result = exchange(RECORDINGS, "", GET, RecordingModel[].class);
            if (result != null) {
                return Arrays.stream(result)
                        .collect(Collectors.toList());
            }
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get recordings", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get recordings", exception);
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
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't stop recording", exception);
        }
    }

    @Override
    public void deleteRecording(String recordingId) throws ClientException {
        try {
            exchange(RECORDINGS + recordingId, "", DELETE, Void.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't delete recording", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't delete recording", exception);
        }
    }
}
