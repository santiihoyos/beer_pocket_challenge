package com.santiihoyos.beerspocket.domain.usecase

import com.santiihoyos.beerspocket.domain.entity.Beer

interface GetBeerByIdUseCase {

    /**
     * Gets a [Beer] by Id from server.
     * @return [Result] of [Beer] when went ok.
     */
    suspend fun getBeerById(id: String): Result<Beer>
}