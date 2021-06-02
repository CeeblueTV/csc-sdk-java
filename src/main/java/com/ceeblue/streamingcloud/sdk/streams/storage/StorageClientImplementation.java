package com.ceeblue.streamingcloud.sdk.streams.storage;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.HTTPMethod;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StorageClientImplementation extends ApiClient implements StorageClient {

    private static final String STORAGES = "/storages/";

    public StorageClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    protected StorageClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public AmazonS3 createStorage(AmazonS3 amazonS3) throws ClientException {
        try {
            String json = createJson(amazonS3);

            return exchange(STORAGES, json, HTTPMethod.POST, AmazonS3.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create storage: " + amazonS3, STORAGES, HTTPMethod.POST, exception);
        }
    }

    @Override
    public AmazonS3 getStorage(String storageId) throws ClientException {
        try {
            return exchange(STORAGES + storageId, "", HTTPMethod.GET, AmazonS3.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get storage: " + storageId, STORAGES + storageId, HTTPMethod.GET, exception);
        }
    }

    @Override
    public List <AmazonS3> getStorages() throws ClientException {
        try {
            AmazonS3[] result = exchange(STORAGES, "", HTTPMethod.GET, AmazonS3[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get storages", session + STORAGES, HTTPMethod.GET, exception);
        }

        throw new ClientException("Can't get storages", session + STORAGES, HTTPMethod.GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public AmazonS3 updateStorage(AmazonS3 storage) throws ClientException {
        try {
            String body = createJson(storage);

            return exchange(STORAGES + storage.getName(), body, HTTPMethod.PUT, AmazonS3.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't update storage: " + storage, STORAGES + storage.getName(), HTTPMethod.PUT, exception);
        }
    }

    @Override
    public void deleteStorage(String storageId) throws ClientException {
        try {
            exchange(STORAGES + storageId, "", HTTPMethod.DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete storage", session + STORAGES + storageId, HTTPMethod.DELETE, exception);
        }
    }

}
