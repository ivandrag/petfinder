package com.petfinder.di

import com.petfinder.networking.provideHttpLoggingInterceptor
import com.petfinder.networking.provideOkHttp
import com.petfinder.networking.provideRetrofit
import org.koin.dsl.module

val appModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }
}
