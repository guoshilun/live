package com.fansan.common.network

import com.fansan.common.BuildConfig
import com.fansan.common.utils.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author  fansan
 *@version 2020/10/20
 */
 
class JKHeadInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = CacheUtil.getToken()
        val builder = chain.request().newBuilder()
            .addHeader("Access-Token", token?:"")
            .removeHeader("User-Agent")
            .addHeader("User-Agent","fansan--1.0.5")
        return chain.proceed(builder.build())
    }

}