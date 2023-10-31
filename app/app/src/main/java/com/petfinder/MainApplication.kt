package com.petfinder

import android.app.Application
import com.petfinder.di.appModule
import com.petfinder.di.dataModule
import com.petfinder.di.domainModule
import com.petfinder.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(
                appModule,
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}