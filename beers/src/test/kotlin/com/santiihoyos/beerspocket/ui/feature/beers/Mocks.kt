package com.santiihoyos.beerspocket.ui.feature.beers

import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beers.data.entity.response.ImageResponse
import com.santiihoyos.beerspocket.domain.entity.Beer

object CharacterMocks {

    val page1 = listOf(
        Beer(
            id = 1,
            name = "Santi",
            description = "HolaCaracola",
            imageUrl = "https://google",
            imageExtension = "png"
        ),
        Beer(
            id = 2,
            name = "Santi2",
            description = "HolaCaracola2",
            imageUrl = "https://google2",
            imageExtension = "png"
        ),
        Beer(
            id = 3,
            name = "Santi3",
            description = "HolaCaracola3",
            imageUrl = "https://google3",
            imageExtension = "png"
        )
    )

    val characterResponses = listOf(
        BeerResponse(
            id = 1,
            name = "Santi",
            description = "HolaCaracola",
            thumbnail = ImageResponse(
                path = "https://google",
                extension = "png"
            ),
            modified = null,
            resourceUri = null
        ),
        BeerResponse(
            id = 2,
            name = "Santi2",
            description = "HolaCaracola2",
            thumbnail = ImageResponse(
                path = "https://google2",
                extension = "png"
            ),
            modified = null,
            resourceUri = null
        ),
        BeerResponse(
            id = 3,
            name = "Santi3",
            description = "HolaCaracola3",
            thumbnail = ImageResponse(
                path = "https://google3",
                extension = "png"
            ),
            modified = null,
            resourceUri = null
        )
    )
}