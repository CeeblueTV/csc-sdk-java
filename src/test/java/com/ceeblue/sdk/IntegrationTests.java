package com.ceeblue.sdk;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.input.InputApiClientImplementation;
import com.ceeblue.sdk.streams.input.InputStreamClient;
import com.ceeblue.sdk.streams.input.StreamBuilder;
import com.ceeblue.sdk.streams.input.models.Access;
import com.ceeblue.sdk.streams.input.models.InputFormat;
import com.ceeblue.sdk.streams.input.models.OutputSettings;
import com.ceeblue.sdk.streams.input.models.SpeedPreset;
import com.ceeblue.sdk.streams.input.models.encoder.EncoderSettings;
import com.ceeblue.sdk.streams.input.models.encoder.H264Settings;
import com.ceeblue.sdk.streams.input.models.inputs.CreatedInput;
import com.ceeblue.sdk.streams.input.models.inputs.Input;
import com.ceeblue.sdk.streams.input.models.tracks.AudioTrack;
import com.ceeblue.sdk.streams.input.models.tracks.VideoTrack;
import com.ceeblue.sdk.streams.output.OutputStreamClient;
import com.ceeblue.sdk.streams.output.models.output.CreatedOutput;
import com.ceeblue.sdk.streams.output.models.output.Output;
import com.ceeblue.sdk.streams.push.models.TrackSelector;
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
import static com.ceeblue.sdk.streams.input.models.tracks.TrackType.Audio;
import static com.ceeblue.sdk.streams.input.models.tracks.TrackType.Video;

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

        Input input = builder.build();

        CreatedInput createdInput = Assertions.assertDoesNotThrow(() -> inputStreamClient.createInput(input), "Try to create input stream");

        Assertions.assertTrue(inputStreamClient.getInputs().size() > 0, "Check getting all input streams");
        Assertions.assertDoesNotThrow(() -> inputStreamClient.getInputs().size() > 0, "Check getting all input streams");

        CreatedInput finalCreatedInput1 = createdInput;
        Assertions.assertDoesNotThrow(() -> inputStreamClient.getInput(finalCreatedInput1.getId()), "Check getting input stream");


        createdInput = Assertions.assertDoesNotThrow(() -> inputStreamClient.updateInput(finalCreatedInput1.getId(), Access.Private, null), "Try to update input stream");
        Assertions.assertEquals(createdInput, inputStreamClient.getInput(createdInput.getId()), "Check updated stream");

        CreatedInput finalCreatedInput = createdInput;

        OutputSettings output = Assertions.assertDoesNotThrow(() -> inputStreamClient.getOutputSettings(finalCreatedInput.getId()), "Try to get output");

        output.setPassthrough(false);
        Assertions.assertEquals(output, inputStreamClient.updateOutputSettings(createdInput.getId(), output), "Try to update output");


        Assertions.assertDoesNotThrow(() -> inputStreamClient.deleteInput(finalCreatedInput.getId()), "Could not delete input stream");

        Assertions.assertThrows(ClientException.class, () -> inputStreamClient.getInput(finalCreatedInput.getId()), "Try to get deleted inputStream");
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

        Input input = builder.build();

        CreatedInput createdInput = Assertions.assertDoesNotThrow(() -> inputStreamClient.createInput(input), "Try to create input stream");

        CreatedOutput createdOutput = outputStreamClient.createOutput(new Output(createdInput.getId(), InputFormat.RTMP));

        List<CreatedOutput> outputs = outputStreamClient.getOutputs(createdInput.getId());

        Assertions.assertTrue(outputs.size() > 0, "Check getting all input streams. Should be at least 1");

        Assertions.assertDoesNotThrow(() -> outputStreamClient.deleteOutput(createdOutput.getId()), "Try to delete output");

        Assertions.assertEquals(0, outputStreamClient.getOutputs(createdInput.getId()).size(), "Check result of deleting");

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
//        String node = "368087c2-42e7-4781-bd5c-63d6ce1bcf06";
//
//        AmazonS3Compatible storage = new AmazonS3Compatible("Test storage", "AK...............PU", "GW.............................f+", "test-recordings", "google..com");
//        AmazonS3Compatible createdStorage = (AmazonS3Compatible) Assertions.assertDoesNotThrow(() -> storageClient.createStorage(storage), "Try to create storage");
//
//
//        List<CreatedRecording> allRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordings(), "Try to get all recordings");
//
//        CreatedRecording createdRecording = recordingClient.createRecording(
//                new Recording(node, "recordingTest", FileFormat.MKV,
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
//        List<CreatedRecording> allStreamRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordingByStreamId(node));
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
//        allStreamRecordings = Assertions.assertDoesNotThrow(() -> recordingClient.getRecordingByStreamId(node));
//
//        Assertions.assertEquals(0, allStreamRecordings.size());
    }

}

