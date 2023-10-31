package com.petfinder.di

import com.petfinder.data.provider.PetFinderAPIProvider
import com.petfinder.networking.PetFinderAPI
import com.petfinder.networking.provideHttpLoggingInterceptor
import com.petfinder.networking.provideOkHttp
import com.petfinder.networking.provideRetrofit
import org.koin.dsl.module

val appModule = module {
    single<PetFinderAPIProvider> { object : PetFinderAPIProvider {
        override fun getPetFinderAPI(): PetFinderAPI = get()
    }}
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp(get(), get()) }
    single { provideRetrofit(get()) }
}
