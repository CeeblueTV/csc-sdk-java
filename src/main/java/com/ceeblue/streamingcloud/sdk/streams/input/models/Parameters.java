package com.ceeblue.streamingcloud.sdk.streams.input.models;

import java.util.Objects;

public class Parameters {
    private String signallingUri;

    public Parameters() {
    }

    public String getSignallingUri() {
        return signallingUri;
    }

    public void setSignallingUri(String signallingUri) {
        this.signallingUri = signallingUri;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "signallingUri='" + signallingUri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return signallingUri.equals(that.signallingUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signallingUri);
    }
}
