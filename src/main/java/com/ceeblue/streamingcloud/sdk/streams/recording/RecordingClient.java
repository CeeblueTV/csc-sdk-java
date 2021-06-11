package com.ceeblue.streamingcloud.sdk.streams.recording;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.Recording;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.created.RecordingModel;

import java.util.List;

/**
 * Client for recording manipulation
 */
public interface RecordingClient {

    /**
     * Create a new recording
     *
     * @param recording recording settings
     * @return created recording
     */
    RecordingModel createRecording(Recording recording) throws ClientException;

    /**
     * Fetching recording
     *
     * @param recordingId id of record {@link RecordingModel}
     */
    RecordingModel getRecording(String recordingId) throws ClientException;

    /**
     * Fetching recording by input stream id
     *
     * @param streamId input stream id {@link InputEndpoint}
     * @return list of records
     */
    List <RecordingModel> getRecordingByStreamId(String streamId) throws ClientException;

    /**
     * Fetch all recordings from server
     *
     * @return list of records
     */
    List <RecordingModel> getRecordings() throws ClientException;

    /**
     * Stop recording
     *
     * @param recordingId id of record {@link RecordingModel}
     */
    void stopRecording(String recordingId) throws ClientException;

    /**
     * Delete recording
     *
     * @param recordingId id of record {@link RecordingModel}
     */
    void deleteRecording(String recordingId) throws ClientException;

}
