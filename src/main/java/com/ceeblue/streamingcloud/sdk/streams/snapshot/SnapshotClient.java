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
     * @param streamId id of input stream
     * @param source   {@link Source} - capturing source [ incoming | outgoing ]
     * @return ByteBuffer with image
     * @throws ClientException if server return error
     */
    ByteBuffer getSnapshotImage(String streamId, Source source) throws ClientException;

    /**
     * fetches a specific snapshot settings
     *
     * @param streamId id of input stream
     * @param source   {@link Source} - capturing source [ incoming | outgoing ]
     * @return ByteBuffer with image
     * @throws ClientException if server return error
     */
    Recording getSnapshotSettings(String streamId, Source source) throws ClientException;

    /**
     * Setup the creation of snapshots
     *
     * @param streamId  id of input stream
     * @param source    {@link Source} capturing source [ incoming | outgoing ]
     * @param recording {@link Recording} settings for applying
     * @throws ClientException if server return error
     */
    void updateSnapshotSettings(Recording recording, String streamId, Source source) throws ClientException;

    /**
     * Delete snapshot settings
     *
     * @param streamId id of input stream
     * @param source   {@link Source} capturing source [ incoming | outgoing ]
     * @throws ClientException if server return error
     */
    void deleteSnapshotSettings(String streamId, Source source) throws ClientException;
}
