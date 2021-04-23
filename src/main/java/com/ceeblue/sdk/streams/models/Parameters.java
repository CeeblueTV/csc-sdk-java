package com.ceeblue.sdk.streams.models;

class Parameters {
    private String signallingUri;

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
}
