package com.example.festivalvar.data.remote.service

import android.os.Build
import com.example.festivalvar.CoreApp
import com.example.festivalvar.utils.AppConstants.API_URL
import com.example.festivalvar.utils.AppConstants.REQUEST_TIMEOUT
import com.example.festivalvar.utils.PrefUtils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceClient {
    private var retrofit: Retrofit? = null

    fun createNetworkClient() = getClient()

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .client(okHttp.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a Custom Interceptor to apply Headers application wide
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request().newBuilder()
        //var user = UserManager(TarlamApplication.context)


        request
            //.addHeader("x-device-type", Build.DEVICE)
            //.addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept","application/json")
            //.addHeader("Authorization", "Bearer " + "web-service")
            //.addHeader("Authorization", "Bearer" + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOC4xOTcuMjU0LjEyMlwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU2ODA2MDMzOSwibmJmIjoxNTY4MDYwMzM5LCJqdGkiOiJHT3FTUHA4aTRpa0JSbWNEIiwic3ViIjoxMywicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.6ZhaiXyScpHvfdHqAaEyxQZlAmc0fgcptXyooJPnYQI")
        if (PrefUtils.isLoggedUser()) {
                request.addHeader("Authorization", "Bearer " + PrefUtils.getToken())
            }

        chain.proceed(request.build())
    }

    private val cacheDir = File(CoreApp.context.cacheDir, UUID.randomUUID().toString())
    // 50 MiB cache
    private val cache = Cache(cacheDir, 50 * 1024 * 1024)

    //.cache(cache)
    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()

        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)

    inline fun <reified T> createWebService(): T {
        return createNetworkClient().create(T::class.java)
    }
}