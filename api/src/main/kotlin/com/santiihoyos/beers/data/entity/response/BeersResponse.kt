package com.santiihoyos.beers.data.entity.response

import com.google.gson.annotations.SerializedName

data class BeerResponse(

    /**
     *
     */
    val id: Int?,

    /**
     *
     */
    val name: String?,

    /**
     *
     */
    val tagLine: String?,

    /**
     *
     */
    val description: String?,

    /**
     *
     */
    @SerializedName("image_url")
    val imageUrl: String?,

    /**
     *
     */
    val abv: Float,

    /**
     *
     */
    val ibu: Float,

    //ADD: rest of properties here...
)