package com.petfinder.networking

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.connectTimeout(5, TimeUnit.MINUTES)
    okHttpClient.readTimeout(5, TimeUnit.MINUTES);
    okHttpClient.apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(httpLoggingInterceptor)
        }
    }
    return okHttpClient.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): PetFinderAPI {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(PetFinderAPI::class.java)
}
