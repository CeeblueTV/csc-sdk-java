package com.ceeblue.sdk;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.input.InputApiClientImplementation;
import com.ceeblue.sdk.streams.input.InputStreamClient;
import com.ceeblue.sdk.streams.input.StreamBuilder;
import com.ceeblue.sdk.streams.input.models.*;
import com.ceeblue.sdk.streams.output.OutputStreamClient;
import com.ceeblue.sdk.streams.output.models.output.CreatedOutput;
import com.ceeblue.sdk.streams.output.models.output.Output;
import com.ceeblue.sdk.streams.push.models.output.TrackSelector;
import com.ceeblue.sdk.streams.recording.RecordingClient;
import com.ceeblue.sdk.streams.recording.models.Capture;
import com.ceeblue.sdk.streams.recording.models.FileFormat;
import com.ceeblue.sdk.streams.recording.models.Recording;
import com.ceeblue.sdk.streams.recording.models.Source;
import com.ceeblue.sdk.streams.recording.models.created.CreatedRecording;
import com.ceeblue.sdk.streams.recording.models.created.State;
import com.ceeblue.sdk.streams.storage.StorageClient;
import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3;
import com.ceeblue.sdk.streams.storage.models.storages.AmazonS3Compatible;
import com.ceeblue.sdk.utils.ClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    OutputStreamClient outputStreamClient;

    @Autowired
    RecordingClient recordingClient;

    @Autowired
    StorageClient storageClient;


    @Test
    void fullInputStreamCycle() {

        StreamBuilder builder = new StreamBuilder(InputFormat.WebRTC)
                .setOutput(
                        new OutputSettings(true)
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

        OutputSettings output = Assertions.assertDoesNotThrow(() -> inputStreamClient.getOutputSettings(finalCreatedStream.getId()), "Try to get output");

        output.setPassthrough(false);
        Assertions.assertEquals(output, inputStreamClient.updateOutput(createdStream.getId(), output), "Try to update output");


        Assertions.assertDoesNotThrow(() -> inputStreamClient.deleteInput(finalCreatedStream.getId()), "Could not delete input stream");

        Assertions.assertThrows(ClientException.class, () -> inputStreamClient.getInput(finalCreatedStream.getId()), "Try to get deleted inputStream");
    }

    @Test
    void fullOutputStreamCycle() {

        inputStreamClient = new InputApiClientImplementation(authenticationClient, restTemplate);

        StreamBuilder builder = new StreamBuilder(InputFormat.WebRTC)
                .setOutput(
                        new OutputSettings(true)
                                .version(1)
                                .tracks(Arrays.asList(
                                        new VideoTrack(Video, new H264Settings(H264, 110, SpeedPreset.faster, 20)),
                                        new AudioTrack(Audio, new EncoderSettings(AAC, 30))
                                ))).setAccess(Access.Private, "xxx");

        Stream stream = builder.build();

        CreatedStream createdStream = Assertions.assertDoesNotThrow(() -> inputStreamClient.createStream(stream), "Try to create input stream");

        CreatedOutput createdOutput = outputStreamClient.createOutput(new Output(createdStream.getId(), InputFormat.RTMP));

        List<CreatedOutput> outputs = outputStreamClient.getOutputs(createdStream.getId());

        Assertions.assertTrue(outputs.size() > 0, "Check getting all input streams. Should be at least 1");

        Assertions.assertDoesNotThrow(() -> outputStreamClient.deleteOutput(createdOutput.getId()), "Try to delete output");

        Assertions.assertEquals(0, outputStreamClient.getOutputs(createdStream.getId()).size(), "Check result of deleting");

        Assertions.assertDoesNotThrow(() -> outputStreamClient.deleteOutputSessions(createdOutput.getId()), "Try to delete output sessions");
    }

    @Test
    void fullStorageCycle() {
        AmazonS3Compatible storage = new AmazonS3Compatible("Test storage", "AK...............PU", "GW.............................f+", "test-recordings", "google..com");

        List<AmazonS3> storages = Assertions.assertDoesNotThrow(() -> storageClient.getStorages(), "Try to get all storages");

        AmazonS3Compatible createdStorage = (AmazonS3Compatible) Assertions.assertDoesNotThrow(() -> storageClient.createStorage(storage), "Try to create storage");

        List<AmazonS3> updatedStorages = Assertions.assertDoesNotThrow(() -> storageClient.getStorages(), "Try to get all storages");

        Assertions.assertTrue(updatedStorages.size() > storages.size());

        AmazonS3Compatible updatedStorage = (AmazonS3Compatible) Assertions.assertDoesNotThrow(() -> storageClient.updateStorage(createdStorage.setEndpoint("google.com")), "Try to update storage");

        Assertions.assertEquals(updatedStorage.getEndpoint(), "google.com");

        Assertions.assertDoesNotThrow(() -> storageClient.deleteStorage(createdStorage.getName()));

        List<AmazonS3> resultOfDelete = Assertions.assertDoesNotThrow(() -> storageClient.getStorages(), "Try to get all storages");

        Assertions.assertEquals(resultOfDelete.size(), storages.size(), "Check result of deleting");
    }

    @Test
    void fullRecordingStreamCycle() {
//        AmazonS3Compatible storage = new AmazonS3Compatible("Test storage", "AK...............PU", "GW.............................f+", "test-recordings", "google..com");
//        AmazonS3Compatible createdStorage = (AmazonS3Compatible) Assertions.assertDoesNotThrow(() -> storageClient.createStorage(storage), "Try to create storage");
//
//
//        List<CreatedRecording> allRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordings(), "Try to get all recordings");
//
//        CreatedRecording createdRecording = recordingClient.createRecording(
//                new Recording("616e70c7-08d3-434a-9f8e-edd9664420b1", "recordingTest", FileFormat.MKV,
//                        new Capture().setSource(Source.Incoming).setTrackSelector(new TrackSelector(Video)), createdStorage.getName()
//
//                )
//        );
//
//        Assertions.assertDoesNotThrow(() -> recordingClient.getRecording(createdRecording.getId()));
//
//        List<CreatedRecording> allRecordingsAfterCreation = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordings(), "Try to get all recordings");
//
//        Assertions.assertTrue(allRecordingsAfterCreation.size() > allRecordings.size());
//
//        List<CreatedRecording> allStreamRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordingByStreamId("616e70c7-08d3-434a-9f8e-edd9664420b1"));
//
//        Assertions.assertEquals(1, allStreamRecordings.size());
//
//        State state;
//
//        Assertions.assertDoesNotThrow(() -> recordingClient.stopRecording(createdRecording.getId()));
//
//        do {
//            state = recordingClient.getRecording(createdRecording.getId()).getState();
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (state != State.Error && state != State.Completed);
//
//
//        Assertions.assertDoesNotThrow(() -> recordingClient.deleteRecording(createdRecording.getId()));
//
//        allStreamRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordingByStreamId("616e70c7-08d3-434a-9f8e-edd9664420b1"));
//
//        Assertions.assertEquals(0, allStreamRecordings.size());
    }

}

