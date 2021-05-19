package com.ceeblue.sdk.streams.output;

import com.ceeblue.sdk.authentiffication.AuthenticationClient;
import com.ceeblue.sdk.http.HttpClient;
import com.ceeblue.sdk.streams.ApiClient;
import com.ceeblue.sdk.streams.output.models.output.CreatedOutput;
import com.ceeblue.sdk.streams.output.models.output.Output;
import com.ceeblue.sdk.utils.ApiCallException;
import com.ceeblue.sdk.utils.ClientException;
import com.ceeblue.sdk.utils.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ceeblue.sdk.http.template.utils.HTTPMethod.*;

@Service
public class OutputApiClientImplementation extends ApiClient implements OutputStreamClient {

    private static final String OUTPUTS = "/outputs";
    private static final String SESSIONS = "/sessions";

    @Autowired
    protected OutputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template) {
        super(authenticationClient, template);
    }

    protected OutputApiClientImplementation(AuthenticationClient authenticationClient, HttpClient template, String endpoint) {
        super(authenticationClient, template, endpoint);
    }

    @Override
    public CreatedOutput createOutput(Output output) {
        try {
            String json = createJson(output);

            return exchange(OUTPUTS, json, POST, CreatedOutput.class);
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't create output stream: " + output, session + OUTPUTS, POST, exception);
        }

    }

    @Override
    public List<CreatedOutput> getOutputs(String streamId) {
        String parts = OUTPUTS + "?streamId=" + streamId;
        try {
            CreatedOutput[] result = exchange(parts, "", GET, CreatedOutput[].class);

            if (result != null) {
                return Arrays.stream(result).collect(Collectors.toList());
            }
        } catch (JsonParseException | ApiCallException exception) {
            throw new ClientException("Can't get output stream", session + parts, GET, exception);
        }

        throw new ClientException("Can't get output stream", session + parts, GET, new RuntimeException("No result from server!!!"));
    }

    @Override
    public void deleteOutputSessions(String outputId) {
        try {
            exchange(OUTPUTS +"/"+ outputId + SESSIONS, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete output stream", session + OUTPUTS + outputId + SESSIONS, DELETE, exception);
        }
    }

    @Override
    public void deleteOutput(String outputId) {
        try {
            exchange(OUTPUTS + "/" + outputId, "", DELETE, Void.class);
        } catch (RuntimeException exception) {
            throw new ClientException("Can't delete stream", session + OUTPUTS + outputId, DELETE, exception);
        }
    }
}
