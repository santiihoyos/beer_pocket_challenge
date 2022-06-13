package com.santiihoyos.beerspocket

import android.app.Application
import com.santiihoyos.beers.di.getApiModule
import com.santiihoyos.beerspocket.di.appModule
import com.santiihoyos.beerspocket.di.charactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BeerPocketApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BeerPocketApplication)
            modules(
                appModule,
                getApiModule(
                    apiBaseUrl = BuildConfig.API_BASE_URL,
                    publicKey = BuildConfig.API_PUB_KEY,
                    privateKey = BuildConfig.API_PRIV_KEY,
                ),
                charactersModule
            )
        }
    }
}