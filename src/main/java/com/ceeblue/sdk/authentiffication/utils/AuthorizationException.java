package com.ceeblue.sdk.authentiffication.utils;

public class AuthorizationException extends RuntimeException {

    public Integer exceptionCode;


    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Integer exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }


}
