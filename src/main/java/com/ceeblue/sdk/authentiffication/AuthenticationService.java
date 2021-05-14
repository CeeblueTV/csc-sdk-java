package com.ceeblue.sdk.authentiffication;

public interface AuthenticationService {
    void authenticate(String username, String password, Session session);
}
