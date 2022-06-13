package com.santiihoyos.beerspocket.domain.extension

import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beerspocket.domain.entity.Beer

/**
 * Extension to map [BeerResponse] to [Beer]
 */
fun BeerResponse.toCharacter(): Beer {
    return Beer(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        abv = this.abv,
        ibu = this.ibu,
    )
}