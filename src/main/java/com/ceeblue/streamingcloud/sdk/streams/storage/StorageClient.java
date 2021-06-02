package com.ceeblue.streamingcloud.sdk.streams.storage;

import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;

import java.util.List;

/**
 * Service for managing storage
 */
public interface StorageClient {

    /**
     * Create a new storage
     *
     * @param amazonS3 Storage to set
     */
    AmazonS3 createStorage(AmazonS3 amazonS3) throws ClientException;

    /**
     * Get storage
     *
     * @param storageId id of storage to delete {@link AmazonS3}
     */
    AmazonS3 getStorage(String storageId) throws ClientException;

    /**
     * Get all storages
     */
    List <AmazonS3> getStorages() throws ClientException;

    /**
     * Update storage
     *
     * @param storage storage settings that will be applied
     */
    AmazonS3 updateStorage(AmazonS3 storage) throws ClientException;

    /**
     * Delete storage
     *
     * @param storageId id of storage to delete {@link AmazonS3}
     */
    void deleteStorage(String storageId) throws ClientException;

}
