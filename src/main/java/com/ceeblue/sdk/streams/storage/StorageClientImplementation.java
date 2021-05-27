package com.ceeblue.sdk.streams.storage;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.ApiClient;
import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

public class StorageClientImplementation extends ApiClient implements StorageClient {

    private static final String STORAGES = "/storages/";

    protected StorageClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    protected StorageClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public AmazonS3 createStorage(AmazonS3 amazonS3) {
        try {
            String json = createJson(amazonS3);

            return exchange(STORAGES, json, POST, AmazonS3.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create storage: " + amazonS3, STORAGES, POST, exception);
        }
    }

    @Override
    public AmazonS3 getStorage(String storageId) {
        try {
            return exchange(STORAGES + storageId, "", GET, AmazonS3.class);

        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get storage: " + storageId, STORAGES + storageId, GET, exception);
        }
    }

    @Override
    public List<AmazonS3> getStorages() {
        try {
            AmazonS3[] result = exchange(STORAGES, "", GET, AmazonS3[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get storages", session + STORAGES, GET, exception);
        }

        throw new ClientException("Can't get storages", session + STORAGES, GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public AmazonS3 updateStorage(AmazonS3 storage) {
        try {
            String body = createJson(storage);

            return exchange(STORAGES + storage.getName(), body, PUT, AmazonS3.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't update storage: " + storage, STORAGES + storage.getName(), PUT, exception);
        }
    }

    @Override
    public void deleteStorage(String storageId) {
        try {
            exchange(STORAGES + storageId, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete storage", session + STORAGES + storageId, DELETE, exception);
        }
    }
}
