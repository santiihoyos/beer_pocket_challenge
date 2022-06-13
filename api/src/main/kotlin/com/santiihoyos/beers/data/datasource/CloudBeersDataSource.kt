package com.santiihoyos.beers.data.datasource

import com.santiihoyos.beers.data.entity.response.BeerResponse

interface CloudBeersDataSource {

    suspend fun getBeers(
        page: Int,
        perPage: Int
    ): List<BeerResponse>

    suspend fun getBeerById(id: String): List<BeerResponse>?
}