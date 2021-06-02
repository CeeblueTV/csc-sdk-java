package com.ceeblue.streamingcloud.sdk.authentiffication;

import com.ceeblue.streamingcloud.sdk.authentiffication.utils.AuthorizationException;

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
