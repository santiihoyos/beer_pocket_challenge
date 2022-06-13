package com.santiihoyos.beers.di

import com.santiihoyos.beers.data.datasource.LocalCharacterDataSource
import com.santiihoyos.beers.data.datasource.impl.LocalCharacterDataSourceImpl
import com.santiihoyos.beers.data.datasource.impl.RestMarvelDataSource
import com.santiihoyos.beers.data.repository.BeerRepository
import com.santiihoyos.beers.data.repository.impl.CharacterRepositoryImpl
import org.koin.dsl.module

fun getApiModule(
    apiBaseUrl: String,
    publicKey: String,
    privateKey: String,
): org.koin.core.module.Module {
    return module {
        single<BeerRepository> {
            CharacterRepositoryImpl(get(), get())
        }

        single {
            print("")
            RestMarvelDataSource.getInstance(
                baseUrl = apiBaseUrl,
                apiKey = publicKey,
                privateKey = privateKey,
            )
        }

        single<LocalCharacterDataSource> {
            LocalCharacterDataSourceImpl()
        }
    }
}