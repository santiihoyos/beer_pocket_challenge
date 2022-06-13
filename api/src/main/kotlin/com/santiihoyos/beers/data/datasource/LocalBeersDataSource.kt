package com.santiihoyos.beers.data.datasource

import com.santiihoyos.beers.data.entity.response.BeerResponse

/**
 * Local storage operations
 */
interface LocalBeersDataSource {

    /**
     * Get saved favorite beers.
     * @return - [List] of [BeerResponse] or empty
     */
    suspend fun getFavoriteBeers(): Result<List<BeerResponse>>

    /**
     * Save into local storage a new Favorite Beer
     */
    suspend fun saveFavoriteBeer(
        beerResponse: BeerResponse
    ): Result<Boolean>

    /**
     * Removes from local storage an favorite beer
     */
    suspend fun deleteFavoriteBeer(
        beerId: String,
    ): Result<List<BeerResponse>>
}