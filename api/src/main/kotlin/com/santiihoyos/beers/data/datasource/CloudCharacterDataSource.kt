package com.santiihoyos.beers.data.datasource

import com.santiihoyos.beers.data.entity.response.BaseResponse
import com.santiihoyos.beers.data.entity.response.BeerResponse

interface CloudCharacterDataSource {

    suspend fun getCharacters(
        orderBy: String,
        offset: Int,
        limit: Int
    ): BaseResponse<BeerResponse>

    suspend fun getCharacterById(id: String): BaseResponse<BeerResponse>
}