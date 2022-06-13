package com.santiihoyos.beers.data.repository.impl

import com.santiihoyos.beers.data.datasource.CloudBeersDataSource
import com.santiihoyos.beers.data.datasource.LocalBeersDataSource
import com.santiihoyos.beers.data.entity.DataError
import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beers.data.repository.BeerRepository

/**
 * By using cloud and room DataSources implements
 * [BeerRepository] specification
 */
internal class BeerRepositoryImpl(
    private val cloudCharacterDataSource: CloudBeersDataSource,
    private val localCharacterDataSource: LocalBeersDataSource,
) : BeerRepository {

    override suspend fun getBeersByPage(
        page: Int,
        perPage: Int
    ): Result<List<BeerResponse>> {
        return try {
            val response = cloudCharacterDataSource.getBeers(page, perPage)
            Result.success(response)
        } catch (ex: Exception) {
            Result.failure(if (ex is DataError) ex else DataError.UnknownError)
        }
    }

    override suspend fun getBeerById(id: String): Result<BeerResponse> {
        return try {
            val response = cloudCharacterDataSource.getBeerById(id)
            return if (response?.get(0) != null) {
                Result.success(response[0])
            } else {
                Result.failure(DataError.NotFoundError)
            }
            ///TODO: manage all possible api code errors here..
        } catch (ex: Exception) {
            Result.failure(if (ex is DataError) ex else DataError.UnknownError)
        }
    }
}