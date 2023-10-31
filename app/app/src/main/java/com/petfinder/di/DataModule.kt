package com.petfinder.di

import com.petfinder.data.remote.AnimalListPagingRemoteDataSource
import org.koin.dsl.module

val dataModule = module {
    factory { AnimalListPagingRemoteDataSource(get()) }
}
