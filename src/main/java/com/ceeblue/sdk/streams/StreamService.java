package com.ceeblue.sdk.streams;

import com.ceeblue.sdk.authentiffication.AuthenticationService;
import com.ceeblue.sdk.settings.Settings;
import com.ceeblue.sdk.streams.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.ceeblue.sdk.streams.models.CodecName.AAC;
import static com.ceeblue.sdk.streams.models.CodecName.VP8;
import static com.ceeblue.sdk.streams.models.TrackType.Audio;
import static com.ceeblue.sdk.streams.models.TrackType.Video;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Service
public class StreamService {

    private final AuthenticationService authenticationService;

    private final Settings settings;

    public StreamService(AuthenticationService authenticationService, Settings settings) {
        this.authenticationService = authenticationService;
        this.settings = settings;
    }


    public Object createStream(Stream stream) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stream);

        CreatedStream result = authenticationService
                .getAuthenticatedBuilder()
                .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .postForObject(settings.getApi() + "/inputs", json, CreatedStream.class);

        return result;
    }

}
