package com.santiihoyos.beers.data.datasource.impl

import com.santiihoyos.beers.data.datasource.LocalCharacterDataSource
import com.santiihoyos.beers.data.entity.response.BeerResponse

internal class LocalCharacterDataSourceImpl :
    LocalCharacterDataSource {

    override suspend fun getFavoriteCharacters(): Result<List<BeerResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteCharacter(characterResponse: BeerResponse): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteCharacter(characterId: String): Result<List<BeerResponse>> {
        TODO("Not yet implemented")
    }
}