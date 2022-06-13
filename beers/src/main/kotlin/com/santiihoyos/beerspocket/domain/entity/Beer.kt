package com.santiihoyos.beerspocket.domain.entity

/**
 * Entity to represent some character of Marvel universe
 *
 */
data class Beer(

    /**
     * The unique ID of the character resource
     */
    val id: Int?,

    /**
     * The name of the character
     */
    val name: String?,

    /**
     * A short bio or description of the character
     */
    val description: String?,

    /**
     * The base image url.
     * With this url image can not be loaded directly
     */
    val imageUrl: String?,

    /**
     *
     */
    val abv: Float,

    /**
     *
     */
    val ibu: Float,
)