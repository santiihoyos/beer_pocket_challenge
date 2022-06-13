package com.santiihoyos.beers.data.repository

import com.santiihoyos.beers.data.entity.response.BeerResponse

interface BeerRepository {

    /**
     * Get all heroes of Disney & Marvel universe
     * This list is paginated.
     *
     * @param offset - current page to read
     * @return HeroesResponse with server response
     */
    suspend fun getBeersByPage(
        page: Int,
        perPage: Int
    ): Result<List<BeerResponse>>

    /**
     * Get Only one hero searching by id
     *
     * @param id - unique id of hero
     * @return HeroesResponse with server response
     */
    suspend fun getBeerById(id: String): Result<BeerResponse>
}