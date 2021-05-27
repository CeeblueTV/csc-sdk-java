package com.ceeblue.sdk.streams.snapshot;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.http.template.utils.MediaType;
import com.ceeblue.sdk.streams.ApiClient;
import com.ceeblue.sdk.streams.recording.models.Source;
import com.ceeblue.sdk.streams.snapshot.models.Recording;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;
import static com.ceeblue.sdk.streams.snapshot.utils.Constants.*;

public class SnapshotClientImplementation extends ApiClient implements SnapshotClient {

    protected SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    protected SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public ByteBuffer getSnapshotImage(String streamId, Source source) {
        try {
            return ByteBuffer.wrap(exchange(SNAPSHOTS + STREAM + streamId + "/" + source.name().toLowerCase(Locale.ROOT) + "/" + IMAGE, "", GET, byte[].class, new HashMap<>(), MediaType.IMAGE));
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get snapshot", SNAPSHOTS + STREAM + streamId + "/" + source.name().toLowerCase(Locale.ROOT) + "/" + IMAGE, GET, exception);
        }
    }

    @Override
    public void updateSnapshotSettings(Recording recording, String streamId, Source source) {
        try {
            String body = createJson(recording);

            exchange(SNAPSHOTS + STREAM + streamId + "/" + source, body, PUT, Void.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't update snapshot settings", SNAPSHOTS + STREAM + streamId + "/" + source, PUT, exception);
        }
    }

    @Override
    public Recording getSnapshotSettings(String streamId, Source source) {
        try {
            return exchange(SNAPSHOTS + STREAM + streamId + "/" + source, "", GET, Recording.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get snapshot settings", SNAPSHOTS + STREAM + streamId + "/" + source, GET, exception);
        }
    }

    @Override
    public void deleteSnapshotSettings(String streamId, Source source) {
        try {
            exchange(SNAPSHOTS + STREAM + streamId + "/" + source, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete snapshot settings", session + SNAPSHOTS + STREAM + streamId + "/" + source, DELETE, exception);
        }
    }
}
