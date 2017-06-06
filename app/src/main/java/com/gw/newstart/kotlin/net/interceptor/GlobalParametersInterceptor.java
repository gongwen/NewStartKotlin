package com.gw.newstart.kotlin.net.interceptor;

import com.gw.newstart.kotlin.utils.InterceptorUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by GongWen on 17/5/25.
 * https://stackoverflow.com/questions/34791244/retrofit2-modifying-request-body-in-okhttp-interceptor
 */

public class GlobalParametersInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String method = originalRequest.method();
        if ("POST".equals(method)) {
            RequestBody originalRequestBody = originalRequest.body();
            String newPostBodyString = InterceptorUtil.RequestBody2String(originalRequestBody);
            RequestBody addedPostBody = new FormBody.Builder()
                    .add("os", "android")
                    .build();
            String addedPostBodyString = InterceptorUtil.RequestBody2String(addedPostBody);
            newPostBodyString = newPostBodyString + (newPostBodyString.length() > 0 ? "&" : "") + addedPostBodyString;
            Request request = originalRequest.newBuilder()
                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), newPostBodyString))
                    .build();
            return chain.proceed(request);
        } else if ("GET".equals(method)) {
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("os", "android")
                    .build();
            Request request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
        return chain.proceed(originalRequest);
    }
}
