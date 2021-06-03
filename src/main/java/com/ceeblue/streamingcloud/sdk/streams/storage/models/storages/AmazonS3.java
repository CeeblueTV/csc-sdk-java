package com.ceeblue.streamingcloud.sdk.streams.storage.models.storages;

import com.ceeblue.streamingcloud.sdk.streams.storage.models.StorageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AmazonS3.class, name = "AmazonS3"),
        @JsonSubTypes.Type(value = AmazonS3Compatible.class, name = "AmazonS3Compatible")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmazonS3 {

    /**
     * Storage id
     */
    private String id;

    /**
     * Storage name
     */
    private String name;

    /**
     * Storage type
     */
    private StorageType type;

    /**
     * An access key ID (for example AKIAIOSFODNN7EXAMPLE)
     */
    private String accessKeyId;

    /**
     * A secret access key (for example,wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY)
     */
    private String secretAccessKey;

    /**
     * Destination bucket
     */
    private String bucket;

    /**
     * Destination path inside bucket
     */
    private String path;

    public AmazonS3(String name, String accessKeyId, String secretAccessKey, String bucket) {
        this();
        this.name = name;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.bucket = bucket;
    }

    public AmazonS3(String name, String accessKeyId, String secretAccessKey, String bucket, String path) {
        this(name, accessKeyId, secretAccessKey, bucket);
        this.path = path;
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

    public String getId() {
        return id;
    }

    public AmazonS3 setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Storage: " +
                (id != null ? "id=" + id : "") +
                (name != null ? ", name=" + name : "") +
                (type != null ? ", type=" + type : "") +
                (accessKeyId != null ? ", accessKeyId=" + accessKeyId : "") +
                (secretAccessKey != null ? ", secretAccessKey=" + secretAccessKey : "") +
                (bucket != null ? ", bucket=" + bucket : "") +
                (path != null ? ", path=" + path : "");
    }

}
