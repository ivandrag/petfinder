package com.petfinder.networking

import com.petfinder.domain.model.AnimalListResponse
import com.petfinder.domain.model.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetFinderAPI {

    @FormUrlEncoded
    @POST(AUTH_TOKEN)
    fun getAuthToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String
    ): Single<TokenResponse>

    @GET(ANIMAL_LIST)
    fun getAllAnimals(
        @Query("page") page: Int
    ): Single<AnimalListResponse>
}
