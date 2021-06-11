package com.ceeblue.streamingcloud.sdk.authentiffication;

public class Credentials {

    /**
     * The username
     */
    private final String username;

    /**
     * The password
     */
    private final String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
