package com.ceeblue.sdk.streams.storage;

import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3;

import java.util.List;

public interface StorageClient {
    AmazonS3 createStorage(AmazonS3 amazonS3);

    AmazonS3 getStorage(String storageId);

    List<AmazonS3> getStorages();

    AmazonS3 updateStorage(AmazonS3 storage);

    void deleteStorage(String storageId);
}
