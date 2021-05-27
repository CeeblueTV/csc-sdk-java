package com.ceeblue.streamingcloud.sdk.streams.recording;


import com.ceeblue.streamingcloud.sdk.streams.recording.models.created.CreatedRecording;
import com.ceeblue.streamingcloud.sdk.streams.recording.models.Recording;

import java.util.List;

public interface RecordingClient {
    CreatedRecording createRecording(Recording recording);

    CreatedRecording getRecording(String recordingId);

    List<CreatedRecording> getRecordingByStreamId(String streamId);

    List<CreatedRecording> getRecordings();

    void stopRecording(String recordingId);

    void deleteRecording(String recordingId);
}
