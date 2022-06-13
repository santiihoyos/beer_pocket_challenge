package com.santiihoyos.beerspocket.di

import com.santiihoyos.beerspocket.domain.usecase.GetBeerByIdUseCase
import com.santiihoyos.beerspocket.domain.usecase.GetPaginatedBeersUseCase
import com.santiihoyos.beerspocket.domain.usecase.impl.GetBeerByIdImpl
import com.santiihoyos.beerspocket.domain.usecase.impl.GetPaginatedBeerImpl
import com.santiihoyos.beerspocket.ui.feature.detail.BeerDetailViewModel
import com.santiihoyos.beerspocket.ui.feature.list.BeerListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {

    single<GetPaginatedBeersUseCase> {
        GetPaginatedBeerImpl(
            beerRepository = get()
        )
    }

    single<GetBeerByIdUseCase> {
        GetBeerByIdImpl(
            beerRepository = get()
        )
    }

    //Resolves CharacterListViewModel
    viewModel {
        BeerListViewModel(get())
    }

    viewModel {
        BeerDetailViewModel(get())
    }
}