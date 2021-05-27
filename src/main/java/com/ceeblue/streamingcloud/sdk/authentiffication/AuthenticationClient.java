package com.ceeblue.streamingcloud.sdk.authentiffication;

import com.ceeblue.streamingcloud.sdk.authentiffication.utils.AuthorizationException;

/**
 * Client that provide auth logic. It's used on all ApiClients
 */
public interface AuthenticationClient {
    /**
     * Get api token from server
     *
     * @return Session with set token
     * @throws AuthorizationException if server deny or endpoint doesn't set
     */
    Session authenticate() throws AuthorizationException;
}
