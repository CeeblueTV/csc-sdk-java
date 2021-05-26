package com.ceeblue.sdk.streams.snapshot;

import com.ceeblue.sdk.streams.recording.models.Source;
import com.ceeblue.sdk.streams.snapshot.models.Recording;

import java.nio.ByteBuffer;

public interface SnapshotClient {
    ByteBuffer getSnapshotImage(String streamId, Source source);

    void updateSnapshotSettings(Recording recording, String streamId, Source source);

    Recording getSnapshotSettings(String streamId, Source source);

    void deleteSnapshotSettings(String streamId, Source source);
}
