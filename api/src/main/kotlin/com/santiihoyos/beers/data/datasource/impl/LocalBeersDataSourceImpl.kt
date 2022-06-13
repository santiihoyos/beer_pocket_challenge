package com.santiihoyos.beers.data.datasource.impl

import com.santiihoyos.beers.data.datasource.LocalBeersDataSource
import com.santiihoyos.beers.data.entity.response.BeerResponse

internal class LocalBeersDataSourceImpl :
    LocalBeersDataSource {

    override suspend fun getFavoriteBeers(): Result<List<BeerResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteBeer(beerResponse: BeerResponse): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteBeer(beerId: String): Result<List<BeerResponse>> {
        TODO("Not yet implemented")
    }
}