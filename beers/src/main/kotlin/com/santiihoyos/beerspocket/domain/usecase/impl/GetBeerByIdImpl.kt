package com.santiihoyos.beerspocket.domain.usecase.impl

import com.santiihoyos.beers.data.entity.DataError
import com.santiihoyos.beers.data.repository.BeerRepository
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.domain.entity.error.BeerError
import com.santiihoyos.beerspocket.domain.extension.toCharacter
import com.santiihoyos.beerspocket.domain.usecase.GetBeerByIdUseCase

class GetBeerByIdImpl(
    private val beerRepository: BeerRepository
) : GetBeerByIdUseCase {

    override suspend fun getBeerById(id: String): Result<Beer> {
        val result = beerRepository.getBeerById(id)
        return if (result.isSuccess && result.getOrNull() != null) {
            Result.success(result.getOrNull()!!.toCharacter())
        } else {
            Result.failure(
                if (result.exceptionOrNull() is DataError.NotFoundError) {
                    BeerError.NotFoundBeerError
                } else {
                    BeerError.UnknownBeerError
                }
            )
        }
    }
}