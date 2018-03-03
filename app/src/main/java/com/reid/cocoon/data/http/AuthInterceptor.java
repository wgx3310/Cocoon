package com.reid.cocoon.data.http;

import com.reid.cocoon.common.content.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID " + Constant.UNSPLASH_CLIENT_ID)
                .build();
        return chain.proceed(request);
    }
}
