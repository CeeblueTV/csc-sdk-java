package com.ceeblue.sdk.http.template.utils;

import com.ceeblue.sdk.utils.ApiCallException;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpErrorHandler implements Interceptor {

    private static final int INTERNAL_ERROR_MIN_CODE = 500;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            if (response.code() < INTERNAL_ERROR_MIN_CODE) {
                throw new ApiCallException("Can't evaluate request", response.code(), response.body() != null ? response.body().string() : null);
            } else {
                throw new ApiCallException("Internal error on remote server", response.code(), response.body() != null ? response.body().string() : null);
            }
        }
        return response;
    }
}
