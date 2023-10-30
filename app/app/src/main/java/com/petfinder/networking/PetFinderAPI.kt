package com.petfinder.networking

import com.petfinder.domain.model.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PetFinderAPI {

    @FormUrlEncoded
    @POST(AUTH_TOKEN)
    fun getAuthToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String
    ): Single<TokenResponse>
}
