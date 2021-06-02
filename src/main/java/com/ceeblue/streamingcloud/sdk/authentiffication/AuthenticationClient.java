package com.ceeblue.streamingcloud.sdk.authentiffication;

/**
 * Client that provide authentication to cloud
 */
public interface AuthenticationClient {

    /**
     * Perform authentication
     *
     * @return Session filled to use in api call
     * @throws AuthorizationException if server deny or endpoint doesn't set
     */
    Session authenticate() throws AuthorizationException;

}
