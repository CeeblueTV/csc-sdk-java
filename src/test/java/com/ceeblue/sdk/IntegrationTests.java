package com.ceeblue.sdk;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.input.InputStreamClient;
import com.ceeblue.sdk.streams.input.InputStreamClientImplementation;
import com.ceeblue.sdk.streams.input.StreamBuilder;
import com.ceeblue.sdk.streams.input.models.*;
import com.ceeblue.sdk.utils.ClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static com.ceeblue.sdk.streams.input.models.CodecName.AAC;
import static com.ceeblue.sdk.streams.input.models.CodecName.H264;
import static com.ceeblue.sdk.streams.input.models.TrackType.Audio;
import static com.ceeblue.sdk.streams.input.models.TrackType.Video;

@SpringBootTest
class IntegrationTests {


    @Autowired
    HttpClient restTemplate;

    @Autowired
    AuthenticationClient authenticationClient;

    @Autowired
    InputStreamClient inputStreamClient;


    @Test
    void fullInputStreamCycle() {

        inputStreamClient = new InputStreamClientImplementation(authenticationClient, restTemplate);

        StreamBuilder builder = new StreamBuilder(InputFormat.WebRTC)
                .setOutput(
                        new Output(true)
                                .version(1)
                                .tracks(Arrays.asList(
                                        new VideoTrack(Video, new H264Settings(H264, 110, SpeedPreset.faster, 20)),
                                        new AudioTrack(Audio, new EncoderSettings(AAC, 30))
                                )));

        Stream stream = builder.build();

        CreatedStream createdStream = Assertions.assertDoesNotThrow(() -> inputStreamClient.createStream(stream), "Try to create input stream");

        Assertions.assertTrue(inputStreamClient.getInputs().size() > 0, "Check getting all input streams");
        Assertions.assertDoesNotThrow(() -> inputStreamClient.getInputs().size() > 0, "Check getting all input streams");

        CreatedStream finalCreatedStream1 = createdStream;
        Assertions.assertDoesNotThrow(() -> inputStreamClient.getInput(finalCreatedStream1.getId()), "Check getting input stream");


        createdStream = Assertions.assertDoesNotThrow(() -> inputStreamClient.updateInput(finalCreatedStream1.getId(), Access.Private, null), "Try to update input stream");
        Assertions.assertEquals(createdStream, inputStreamClient.getInput(createdStream.getId()), "Check updated stream");

        CreatedStream finalCreatedStream = createdStream;

        Output output = Assertions.assertDoesNotThrow(() -> inputStreamClient.getOutput(finalCreatedStream.getId()), "Try to get output");

        output.setPassthrough(false);
        Assertions.assertEquals(output, inputStreamClient.updateOutput(createdStream.getId(), output), "Try to update output");


        Assertions.assertDoesNotThrow(() -> inputStreamClient.deleteInput(finalCreatedStream.getId()), "Could not delete input stream");

        Assertions.assertThrows(ClientException.class, () -> inputStreamClient.getInput(finalCreatedStream.getId()), "Try to get deleted inputStream");
    }

}

