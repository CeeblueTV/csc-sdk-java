package com.ceeblue.streamingcloud.sdk.streams.snapshot;

import com.ceeblue.streamingcloud.sdk.streams.input.models.inputs.InputEndpoint;
import com.ceeblue.streamingcloud.sdk.streams.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.snapshot.models.Snapshot;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;

import java.nio.ByteBuffer;

/**
 * Service for stream snapshot operations
 */
public interface SnapshotClient {
    /**
     * Fetch stream snapshot
     *
     * @param streamId id of {@link InputEndpoint}
     * @param source capturing source [ incoming | outgoing ]
     * @return ByteBuffer with image
     */
    ByteBuffer getSnapshotImage(String streamId, Source source) throws ClientException;

    /**
     * Fetches a specific snapshot settings
     *
     * @param streamId id of {@link InputEndpoint}
     * @param source   capturing source [ incoming | outgoing ]
     */
    Snapshot getSnapshotSettings(String streamId, Source source) throws ClientException;

    /**
     * Setup the creation of snapshots
     *
     * @param streamId  id of input stream
     * @param source    capturing source [ incoming | outgoing ]
     * @param snapshot settings for applying
     */
    void updateSnapshotSettings(Snapshot snapshot, String streamId, Source source) throws ClientException;

    /**
     * Delete snapshot settings
     *
     * @param streamId id of input stream {@link InputEndpoint}
     * @param source   capturing source [ incoming | outgoing ]
     */
    void deleteSnapshotSettings(String streamId, Source source) throws ClientException;
}
