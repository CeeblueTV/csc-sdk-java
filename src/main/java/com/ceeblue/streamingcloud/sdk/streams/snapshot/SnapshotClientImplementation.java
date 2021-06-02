package com.ceeblue.streamingcloud.sdk.streams.snapshot;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.MediaType;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.snapshot.models.Snapshot;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StingFormatter.getServerMessage;

public class SnapshotClientImplementation extends ApiClient implements SnapshotClient {

    private static final String SNAPSHOTS = "/snapshots/";

    private static final String STREAM = "stream/";

    private static final String IMAGE = "image";

    public SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public ByteBuffer getSnapshotImage(String streamId, Source source) throws ClientException {
        try {
            return ByteBuffer.wrap(exchange(SNAPSHOTS + STREAM + streamId + "/" + source.name().toLowerCase(Locale.ROOT) + "/" + IMAGE, "", GET, byte[].class, new HashMap <>(), MediaType.IMAGE));
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get snapshot", exception);
        } catch (ApiCallException exception) {
             String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get snapshot", exception);
        }
    }

    @Override
    public void updateSnapshotSettings(Snapshot snapshot, String streamId, Source source) throws ClientException {
        try {
            String body = createJson(snapshot);

            exchange(SNAPSHOTS + STREAM + streamId + "/" + source, body, PUT, Void.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't update snapshot settings", exception);
        } catch (ApiCallException exception) {
             String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't update snapshot settings", exception);
        }
    }

    @Override
    public Snapshot getSnapshotSettings(String streamId, Source source) throws ClientException {
        try {
            return exchange(SNAPSHOTS + STREAM + streamId + "/" + source, "", GET, Snapshot.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get snapshot settings", exception);
        } catch (ApiCallException exception) {
             String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get snapshot settings", exception);
        }
    }

    @Override
    public void deleteSnapshotSettings(String streamId, Source source) throws ClientException {
        try {
            exchange(SNAPSHOTS + STREAM + streamId + "/" + source, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete snapshot settings", exception);
        }
    }

}
