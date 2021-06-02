package com.ceeblue.streamingcloud.sdk.streams.recording;


import com.ceeblue.streamingcloud.sdk.streams.recording.models.Recording;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.created.CreatedRecording;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.util.List;

/**
 * Service for stream recording operations
 */
public interface RecordingClient {
    /**
     * Create a new recording
     *
     * @param recording recording settings
     */
    CreatedRecording createRecording(Recording recording) throws ClientException;

    /**
     * Fetching recording
     *
     * @param recordingId id of record {@link CreatedRecording}
     */
    CreatedRecording getRecording(String recordingId) throws ClientException;

    /**
     * Fetching recording by input stream id
     *
     * @param streamId input stream id {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.CreatedInput}
     */
    List<CreatedRecording> getRecordingByStreamId(String streamId) throws ClientException;

    /**
     * Fetch all recordings from server
     *
     * @return list of {@link CreatedRecording}
     */
    List<CreatedRecording> getRecordings() throws ClientException;

    /**
     * Stop recording
     *
     * @param recordingId id of record {@link CreatedRecording}
     */
    void stopRecording(String recordingId) throws ClientException;

    /**
     * Delete recording
     *
     * @param recordingId id of record {@link CreatedRecording}
     */
    void deleteRecording(String recordingId) throws ClientException;
}