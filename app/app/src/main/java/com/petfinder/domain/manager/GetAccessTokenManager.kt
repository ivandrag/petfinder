package com.petfinder.domain.manager

import io.reactivex.rxjava3.core.Single

interface GetAccessTokenManager {

    fun getToken(): Single<String>
}
