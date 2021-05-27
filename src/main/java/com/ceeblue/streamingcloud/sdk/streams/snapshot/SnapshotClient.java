package com.ceeblue.streamingcloud.sdk.streams.snapshot;

import com.ceeblue.streamingcloud.sdk.streams.recording.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.snapshot.models.Recording;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.nio.ByteBuffer;

/**
 * Service for stream snapshot operations
 */
public interface SnapshotClient {
    /**
     * Fetch stream snapshot
     *
     * @param streamId id of {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.CreatedInput}
     * @return ByteBuffer with image
     */
    ByteBuffer getSnapshotImage(String streamId, Source source) throws ClientException;

    /**
     * fetches a specific snapshot settings
     *
     * @param streamId id of {@link com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.CreatedInput}
     * @param source   capturing source
     */
    Recording getSnapshotSettings(String streamId, Source source) throws ClientException;

    /**
     * Setup the creation of snapshots
     *
     * @param streamId  id of input stream
     * @param source    {@link Source} capturing source [ incoming | outgoing ]
     * @param recording {@link Recording} settings for applying
     */
    void updateSnapshotSettings(Recording recording, String streamId, Source source) throws ClientException;

    /**
     * Delete snapshot settings
     *
     * @param streamId id of input stream
     * @param source   {@link Source} capturing source
     */
    void deleteSnapshotSettings(String streamId, Source source) throws ClientException;
}
