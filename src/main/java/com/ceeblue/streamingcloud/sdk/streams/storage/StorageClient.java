package com.ceeblue.streamingcloud.sdk.streams.storage;

import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;

import java.util.List;

/**
 * Service for managing storage
 */
public interface StorageClient {

    /**
     * Create a new storage
     *
     * @param amazonS3 Storage to set
     * @return storage settings
     */
    AmazonS3 createStorage(AmazonS3 amazonS3) throws ClientException;

    /**
     * Get storage
     *
     * @param storageId id of storage to delete {@link AmazonS3}
     * @return storage settings
     */
    AmazonS3 getStorage(String storageId) throws ClientException;

    /**
     * Get all storages
     *
     * @return all storages settings
     */
    List<AmazonS3> getStorages() throws ClientException;

    /**
     * Update storage
     *
     * @param storage storage settings that will be applied
     * @return updated storage settings
     */
    AmazonS3 updateStorage(AmazonS3 storage) throws ClientException;

    /**
     * Delete storage
     *
     * @param storageId id of storage to delete {@link AmazonS3}
     */
    void deleteStorage(String storageId) throws ClientException;

}
