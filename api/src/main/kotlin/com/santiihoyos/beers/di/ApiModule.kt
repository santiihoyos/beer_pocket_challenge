package com.santiihoyos.beers.di

import com.santiihoyos.beers.data.datasource.LocalBeersDataSource
import com.santiihoyos.beers.data.datasource.impl.LocalBeersDataSourceImpl
import com.santiihoyos.beers.data.datasource.impl.RestBeersDataSource
import com.santiihoyos.beers.data.repository.BeerRepository
import com.santiihoyos.beers.data.repository.impl.BeerRepositoryImpl
import org.koin.dsl.module

fun getApiModule(
    apiBaseUrl: String,
): org.koin.core.module.Module {
    return module {
        single<BeerRepository> {
            BeerRepositoryImpl(get(), get())
        }

        single {
            print("")
            RestBeersDataSource.getInstance(
                baseUrl = apiBaseUrl
            )
        }

        single<LocalBeersDataSource> {
            LocalBeersDataSourceImpl()
        }
    }
}