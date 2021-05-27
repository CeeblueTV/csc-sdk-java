package com.ceeblue.streamingcloud.sdk.streams.snapshot;

import com.ceeblue.streamingcloud.sdk.streams.recording.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.snapshot.models.Recording;

import java.nio.ByteBuffer;

public interface SnapshotClient {
    ByteBuffer getSnapshotImage(String streamId, Source source);

    void updateSnapshotSettings(Recording recording, String streamId, Source source);

    Recording getSnapshotSettings(String streamId, Source source);

    void deleteSnapshotSettings(String streamId, Source source);
}
