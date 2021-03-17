package com.raywenderlich.chuckyfacts.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.raywenderlich.chuckyfacts.BuildConfig
import com.raywenderlich.chuckyfacts.data.remote.interceptor.HeaderInterceptor
import com.raywenderlich.chuckyfacts.data.remote.service.AuthenticationService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun <T> create(converterFactory: Converter.Factory,
                   callAdapterFactory: CallAdapter.Factory,
                   serviceType: Class<T>,
                   endPoint: String) =
            retrofitClient(converterFactory,
                    callAdapterFactory,
                    serviceType,
                    httpClient(),
                    endPoint)

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

    private fun <T> retrofitClient(converterFactory: Converter.Factory,
                                   callAdapterFactory: CallAdapter.Factory,
                                   serviceType: Class<T>,
                                   httpClient: OkHttpClient,
                                   endPoint: String): T =
            Retrofit.Builder()
                    .baseUrl(endPoint)
                    .client(httpClient)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .build()
                    .create(serviceType)
}