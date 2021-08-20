package com.example.mysharedpreferencesproject.restapi

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader("Content-Type", "application/json;charset=utf-8")
        return chain.proceed(requestBuilder.build())
    }
}