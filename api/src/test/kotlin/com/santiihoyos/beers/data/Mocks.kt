package com.santiihoyos.beers.data

import com.santiihoyos.beers.data.entity.response.BeerResponse

object Mocks {

    val beersResponses = listOf(
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

    val response200 = listOf(
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

    val baseResponse404 = listOf<BeerResponse>()

    val baseResponse500 = null
}