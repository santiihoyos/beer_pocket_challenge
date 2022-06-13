package com.santiihoyos.beers.data.datasource.impl

import com.santiihoyos.beers.data.datasource.CloudBeersDataSource
import com.santiihoyos.beers.data.entity.response.BeerResponse
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface and default implementation for
 * [CloudBeersDataSource]
 */
internal interface RestBeersDataSource : CloudBeersDataSource {

    /**
     * Endpoint definition to get a list of [BeerResponse]
     */
    @GET("beers")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BeerResponse>

    @GET("beers/{id}")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getBeerById(
        @Path("id") id: String
    ): List<BeerResponse>

    //Add more Marvel api endpoints here.

    companion object {

        private const val HEADER_ACCEPT_JSON = "Accept: application/json"

        private lateinit var instance: CloudBeersDataSource

        /**
         * Build if it is necessary create RetrofitRestRepository or existing instance
         *
         * @return RetrofitRestRepository implementation instance
         */
        fun getInstance(
            baseUrl: String
        ): CloudBeersDataSource {

            if (!Companion::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestBeersDataSource::class.java)
            }

            return instance
        }

        /**
         * Builds http client for intercept all calls and add required query params.
         * also add some interceptors to log request and response
         *
         * @return OkHttpClient - http client with interceptors
         */
        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                })
            }.build()
        }
    }
}