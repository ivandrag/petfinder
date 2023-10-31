package com.petfinder.data.manager

import com.petfinder.data.provider.PetFinderAPIProvider
import com.petfinder.domain.manager.GetAccessTokenManager
import com.petfinder.networking.CLIENT_ID
import com.petfinder.networking.CLIENT_SECRET
import com.petfinder.networking.GRANT_TYPE
import com.petfinder.networking.PetFinderAPI
import io.reactivex.rxjava3.core.Single

class GetAccessTokenManagerImpl(
    private val petFinderAPIProvider: PetFinderAPIProvider
) : GetAccessTokenManager {

    private var accessToken: String? = null
    private val petFinderAPI: PetFinderAPI
        get() = petFinderAPIProvider.getPetFinderAPI()

    override fun getToken(): Single<String> {
        return if (accessToken == null) {
            fetchNewToken()
        } else {
            Single.just(accessToken!!)
        }
    }

    private fun fetchNewToken(): Single<String> {
        return petFinderAPI.getAuthToken(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE)
            .doOnSuccess { newToken ->
                accessToken = newToken.accessToken
            }
            .map { newToken ->
                newToken.accessToken
            }
    }
}
