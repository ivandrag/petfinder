package com.petfinder.di

import com.petfinder.data.manager.GetAccessTokenManagerImpl
import com.petfinder.data.repository.AnimaListRepositoryImpl
import com.petfinder.domain.manager.GetAccessTokenManager
import com.petfinder.domain.repository.AnimalListRepository
import org.koin.dsl.module

val domainModule = module {
    factory<AnimalListRepository> { AnimaListRepositoryImpl(get()) }
    factory<GetAccessTokenManager> { GetAccessTokenManagerImpl(get()) }
}
