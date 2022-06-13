package com.santiihoyos.beerspocket.domain.entity.error


sealed class BeerError : Exception() {

    object NotFoundBeerError : BeerError()

    object UnknownBeerError : BeerError()
}