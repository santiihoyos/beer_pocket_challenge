package com.santiihoyos.beers.data.repository.impl

import com.santiihoyos.beers.data.datasource.CloudCharacterDataSource
import com.santiihoyos.beers.data.datasource.LocalCharacterDataSource
import com.santiihoyos.beers.data.entity.DataError
import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beers.data.repository.BeerRepository

/**
 * By using cloud and room DataSources implements
 * [BeerRepository] specification
 */
internal class CharacterRepositoryImpl(
    private val cloudCharacterDataSource: CloudCharacterDataSource,
    private val localCharacterDataSource: LocalCharacterDataSource,
) : BeerRepository {

    override suspend fun getBeersByPage(
        orderBy: String,
        offset: Int,
        limit: Int
    ): Result<List<BeerResponse>> {
        return try {
            val response = cloudCharacterDataSource.getCharacters(orderBy, offset, limit)
            if (response.httpCode == 200 && response.data?.results != null) {
                Result.success(response.data.results)
            } else if (response.httpCode == 404) {
                Result.failure(DataError.NotFoundError)
            } else {
                Result.failure(DataError.UnknownError)
            }
        } catch (ex: Exception) {
            Result.failure(if (ex is DataError) ex else DataError.UnknownError)
        }
    }

    override suspend fun getBeerById(id: String): Result<BeerResponse> {
        return try {
            val response = cloudCharacterDataSource.getCharacterById(id)
            if (response.httpCode == 200 && response.data?.results != null) {
                Result.success(response.data.results[0])
            } else if (response.httpCode == 404) {
                Result.failure(DataError.NotFoundError)
            } else {
                Result.failure(DataError.UnknownError)
            }
            ///TODO: manage all possible api code errors here..
        } catch (ex: Exception) {
            Result.failure(if (ex is DataError) ex else DataError.UnknownError)
        }
    }
}