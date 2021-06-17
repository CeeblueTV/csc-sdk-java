package com.ceeblue.streamingcloud.sdk.streams.input.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Input stream parameters
 */
public class Parameters {

    /**
     * Dynamic input stream parameters
     */
    private Map <String, Object> parameters;

    public Parameters() {
    }

    private Map <String, Object> getParameters() {
        return parameters;
    }

    private void setParameters(Map <String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return parameters.entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }
    }
