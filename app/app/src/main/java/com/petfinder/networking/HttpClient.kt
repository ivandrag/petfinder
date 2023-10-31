package com.petfinder.networking

import com.petfinder.domain.manager.GetAccessTokenManager
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AuthTokenAuthenticator(
    private val getAccessTokenManager: GetAccessTokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val newToken = getAccessTokenManager.getToken().blockingGet()
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun provideOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    getAccessTokenManager: GetAccessTokenManager
): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.apply {
        connectTimeout(5, TimeUnit.MINUTES)
        readTimeout(5, TimeUnit.MINUTES)
        addInterceptor(httpLoggingInterceptor)
        authenticator(AuthTokenAuthenticator(getAccessTokenManager))
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
