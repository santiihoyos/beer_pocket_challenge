package com.santiihoyos.beers.data

import com.santiihoyos.beers.data.entity.response.BaseResponse
import com.santiihoyos.beers.data.entity.response.BeerResponse
import com.santiihoyos.beers.data.entity.response.ImageResponse
import com.santiihoyos.beers.data.entity.response.PaginatedResponse

object Mocks {

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

    val baseResponse200 = BaseResponse(
        httpCode = 200,
        message = null,
        etg = "etg",
        data = PaginatedResponse(
            limit = 3,
            count = 3,
            offset = 1,
            total = 3,
            results = characterResponses
        )
    )

    val baseResponse404 = BaseResponse(
        httpCode = 404,
        message = null,
        etg = "etg",
        data = PaginatedResponse(
            limit = 0,
            count = 0,
            offset = 0,
            total = 0,
            results = emptyList<BeerResponse>()
        )
    )

    val baseResponse500 = BaseResponse(
        httpCode = 500,
        message = null,
        etg = "etg",
        data = PaginatedResponse(
            limit = 0,
            count = 0,
            offset = 0,
            total = 0,
            results = emptyList<BeerResponse>()
        )
    )
}