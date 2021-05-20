package com.ceeblue.sdk.streams.storage.models.storages;

import com.ceeblue.sdk.streams.storage.models.StorageType;
import com.ceeblue.sdk.streams.storage.utils.StorageDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(using = StorageDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmazonS3 {
    private String name;
    private StorageType type;
    private String accessKeyId;
    private String secretAccessKey;
    private String bucket;
    private String path;

    public AmazonS3(String name, String accessKeyId, String secretAccessKey, String bucket) {
        this.name = name;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.bucket = bucket;
        type = StorageType.AmazonS3;
    }

    public AmazonS3() {
        type = StorageType.AmazonS3;
    }

    public String getName() {
        return name;
    }

    public AmazonS3 setName(String name) {
        this.name = name;
        return this;
    }

    public StorageType getType() {
        return type;
    }

    public AmazonS3 setType(StorageType type) {
        this.type = type;
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public AmazonS3 setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public AmazonS3 setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public AmazonS3 setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getPath() {
        return path;
    }

    public AmazonS3 setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "AmazonS3{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", secretAccessKey='" + secretAccessKey + '\'' +
                ", bucket='" + bucket + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
