package com.santiihoyos.beerspocket.ui.feature.beers

import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beerspocket.domain.entity.Beer

object CharacterMocks {

    val page1 = listOf(
        Beer(
            id = 1,
            name = "Santi",
            description = "HolaCaracola",
            imageUrl = "https://google",
            abv = 1.0f,
            ibu = 1.0f
        ),
        Beer(
            id = 2,
            name = "Santi2",
            description = "HolaCaracola2",
            imageUrl = "https://google2",
            abv = 1.0f,
            ibu = 1.0f
        ),
        Beer(
            id = 3,
            name = "Santi3",
            description = "HolaCaracola3",
            imageUrl = "https://google3",
            abv = 1.0f,
            ibu = 1.0f
        )
    )

    val characterResponses = listOf(
        BeerResponse(
            id = 1,
            name = "Santi",
            description = "HolaCaracola",
            imageUrl = "https://google",
            abv = 1.0f,
            ibu = 1.0f,
            tagLine = ""
        ),
        BeerResponse(
            id = 2,
            name = "Santi2",
            description = "HolaCaracola2",
            imageUrl = "https://google",
            abv = 1.0f,
            ibu = 1.0f,
            tagLine = "",
        ),
        BeerResponse(
            id = 3,
            name = "Santi3",
            description = "HolaCaracola3",
            imageUrl = "https://google",
            abv = 1.0f,
            ibu = 1.0f,
            tagLine = "",
        )
    )
}