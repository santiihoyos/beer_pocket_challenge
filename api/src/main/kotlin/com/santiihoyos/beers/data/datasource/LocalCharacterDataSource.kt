package com.santiihoyos.beers.data.datasource

import com.santiihoyos.beers.data.entity.response.BeerResponse

/**
 * Local storage operations
 */
interface LocalCharacterDataSource {

    /**
     * Get saved favorite characters.
     * @return - [List] of [BeerResponse] or empty
     */
    suspend fun getFavoriteCharacters(): Result<List<BeerResponse>>

    /**
     * Save into local storage a new Favorite Character
     */
    suspend fun saveFavoriteCharacter(
        characterResponse: BeerResponse
    ): Result<Boolean>

    /**
     * Removes from local storage an favorite Character
     */
    suspend fun deleteFavoriteCharacter(
        characterId: String,
    ): Result<List<BeerResponse>>
}