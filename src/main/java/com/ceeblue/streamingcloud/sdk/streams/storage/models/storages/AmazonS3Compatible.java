package com.ceeblue.streamingcloud.sdk.streams.storage.models.storages;

import com.ceeblue.streamingcloud.sdk.streams.storage.models.StorageType;

public class AmazonS3Compatible extends AmazonS3 {

    /**
     * A URL that is the entry point for a web service
     */
    private String endpoint;

    public AmazonS3Compatible(String name, String accessKeyId, String secretAccessKey, String bucket, String endpoint) {
        this(name, accessKeyId, secretAccessKey, bucket, null, endpoint);
    }

    public AmazonS3Compatible(String name, String accessKeyId, String secretAccessKey, String bucket, String path, String endpoint) {
        super(name, accessKeyId, secretAccessKey, bucket, path);
        this.endpoint = endpoint;

        this.setType(StorageType.AmazonS3Compatible);
    }

    public AmazonS3Compatible() {
        this.setType(StorageType.AmazonS3Compatible);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public AmazonS3Compatible setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }


    @Override
    public String toString() {
        return super.toString() + " endpoint='" + endpoint + '\'';
    }

}
