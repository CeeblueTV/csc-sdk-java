package com.ceeblue.streamingcloud.sdk.streams.snapshot;

import com.ceeblue.streamingcloud.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.streamingcloud.sdk.http.HttpClient;
import com.ceeblue.streamingcloud.sdk.http.MediaType;
import com.ceeblue.streamingcloud.sdk.http.ResponseInfo;
import com.ceeblue.streamingcloud.sdk.streams.ApiClient;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ApiCallException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.ClientException;
import com.ceeblue.streamingcloud.sdk.streams.exceptions.JsonParseException;
import com.ceeblue.streamingcloud.sdk.streams.models.Source;
import com.ceeblue.streamingcloud.sdk.streams.snapshot.models.SnapshotSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;

import static com.ceeblue.streamingcloud.sdk.http.HTTPMethod.*;
import static com.ceeblue.streamingcloud.sdk.streams.utils.StingFormatter.getServerMessage;

public class SnapshotClientImplementation extends ApiClient implements SnapshotClient {

    private static final String SNAPSHOTS = "/snapshots/";

    private static final String STREAM = "stream/";

    private static final String IMAGE = "image";

    private static final String SNAPSHOT = "snapshot.";

    private static final String CONTENT_TYPE = "Content-Type";

    public SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    public SnapshotClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public File getSnapshotImage(String streamId, Source source) throws ClientException {
        ResponseInfo responseInfo = null;
        try {
            responseInfo = exchange(SNAPSHOTS + STREAM + streamId + "/" + source.name().toLowerCase(Locale.ROOT) + "/" + IMAGE, "", GET, new HashMap <>(), MediaType.IMAGE);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responseInfo.getBody());
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            String imageFormat = responseInfo.getHeaders().get(CONTENT_TYPE).split("/")[1];

            File snapshot = new File(LocalDateTime.now() + SNAPSHOT + imageFormat);
            ImageIO.write(bufferedImage, imageFormat, snapshot);

            return snapshot;
        } catch (JsonParseException exception) {
            throw new ClientException("Can't get snapshot", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't get snapshot", exception);
        } catch (IOException e) {
            throw new ClientException("Couldn't create snapshot image from gotten byte array: " + responseInfo, new RuntimeException(e.getMessage()));
        }
    }

    @Override
    public void updateSnapshotSettings(SnapshotSettings snapshotSettings, String streamId, Source source) throws ClientException {
        try {
            String body = createJson(snapshotSettings);

            exchange(SNAPSHOTS + STREAM + streamId + "/" + source, body, PUT, Void.class);
        } catch (JsonParseException exception) {
            throw new ClientException("Can't update snapshot settings", exception);
        } catch (ApiCallException exception) {
            String serverMessage = getServerMessage(exception.getServerResponse());
            throw new ClientException(serverMessage != null ? serverMessage : "Can't update snapshot settings", exception);
        }
    }

    @Override
    public SnapshotSettings getSnapshotSettings(String streamId, Source source) throws ClientException {
        try {
            return exchange(SNAPSHOTS + STREAM + streamId + "/" + source, "", GET, SnapshotSettings.class);
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
