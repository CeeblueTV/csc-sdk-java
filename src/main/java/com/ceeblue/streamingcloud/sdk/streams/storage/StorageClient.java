package com.ceeblue.streamingcloud.sdk.streams.storage;

import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.streamingcloud.sdk.utils.ClientException;

import java.util.List;

/**
 * Service for managing storage
 */
public interface StorageClient {
    /**
     * Create a new storage
     *
     * @param amazonS3 Storage type
     * @return Created storage {@link AmazonS3}
     * @throws ClientException if server return error
     */
    AmazonS3 createStorage(AmazonS3 amazonS3) throws ClientException;

    /**
     * Get storage
     *
     * @param storageId id of storage
     * @return storage {@link AmazonS3}
     * @throws ClientException if server return error
     */
    AmazonS3 getStorage(String storageId) throws ClientException;

    /**
     * Get all storages
     *
     * @return list of storage {@link AmazonS3}
     * @throws ClientException if server return error
     */
    List<AmazonS3> getStorages() throws ClientException;

    /**
     * Update storage
     *
     * @param storage storage settings that will be applied
     * @return updated storage {@link AmazonS3}
     * @throws ClientException if server return error
     */
    AmazonS3 updateStorage(AmazonS3 storage) throws ClientException;

    /**
     * Delete storage
     *
     * @param storageId id of storage to delete
     * @throws ClientException if server return error
     */
    void deleteStorage(String storageId) throws ClientException;
}
