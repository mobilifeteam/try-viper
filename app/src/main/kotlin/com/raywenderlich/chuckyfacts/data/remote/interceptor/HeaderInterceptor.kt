package com.raywenderlich.chuckyfacts.data.remote.interceptor

import android.os.Build
import com.raywenderlich.chuckyfacts.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val initRequest = chain.request()

        val builder = initRequest.newBuilder()
        builder.header(
                "x-device",
                "Google (${Build.MANUFACTURER} ${Build.MODEL}) android (${Build.VERSION.RELEASE})"
        )
        builder.header("x-application", "SUMO CASH WITHDRAWAL (" + BuildConfig.VERSION_NAME + ")")
        builder.header("accept-language", "th")

        val request = builder
                .method(initRequest.method, initRequest.body)
                .build()

        return chain.proceed(request)
    }
}