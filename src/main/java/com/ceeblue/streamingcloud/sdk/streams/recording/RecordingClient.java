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
     * @param recording {@link Recording} must  contains: input stream id,
     *                  filename,
     *                  {@link com.ceeblue.streamingcloud.sdk.streams.recording.models.FileFormat}
     *                  {@link com.ceeblue.streamingcloud.sdk.streams.recording.models.Capture}
     *                  storage id
     * @return {@link CreatedRecording}
     * @throws ClientException if server return error
     */
    CreatedRecording createRecording(Recording recording) throws ClientException;

    /**
     * Fetching recording
     *
     * @param recordingId id of record
     * @return {@link CreatedRecording}
     * @throws ClientException if server return error
     */
    CreatedRecording getRecording(String recordingId) throws ClientException;

    /**
     * Fetching recording by input stream id
     *
     * @param streamId input stream id
     * @return list of {@link CreatedRecording}
     * @throws ClientException if server return error
     */
    List<CreatedRecording> getRecordingByStreamId(String streamId) throws ClientException;

    /**
     * Fetch all recordings from server
     *
     * @return list of {@link CreatedRecording}
     * @throws ClientException if server return error
     */
    List<CreatedRecording> getRecordings() throws ClientException;

    /**
     * Stop recording
     *
     * @param recordingId id of recording
     * @throws ClientException if server return error
     */
    void stopRecording(String recordingId) throws ClientException;

    /**
     * Delete recording
     *
     * @param recordingId id of recording
     * @throws ClientException if server return error
     */
    void deleteRecording(String recordingId) throws ClientException;
}
