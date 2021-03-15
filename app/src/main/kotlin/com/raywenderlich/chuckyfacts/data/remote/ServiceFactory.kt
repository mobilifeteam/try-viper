package com.raywenderlich.chuckyfacts.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.raywenderlich.chuckyfacts.data.remote.interceptor.HeaderInterceptor
import com.raywenderlich.chuckyfacts.data.remote.service.AuthenticationService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {
    private val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val callAdapterFactory: CallAdapter.Factory = CoroutineCallAdapterFactory()

    fun createNetworkClient(endPoint: String) = retrofitClient(httpClient(), endPoint)

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    private fun retrofitClient(httpClient: OkHttpClient, endPoint: String): AuthenticationService =
            Retrofit.Builder()
                    .baseUrl(endPoint)
                    .client(httpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(callAdapterFactory)
                    .build().create(AuthenticationService::class.java)
}