package com.santiihoyos.beerspocket.domain.usecase

import androidx.paging.PagingSource
import com.santiihoyos.beerspocket.domain.entity.Beer

interface GetPaginatedBeersUseCase {

    /**
     * Items per load page
     */
    var itemsPerPage: Int

    /**
     * Get a page for [Beer]
     * @param page - current page to load
     * @return [Result] of [List] of [Beer] if next items were loaded ok, manage failure
     *          to known there is error.
     */
    suspend fun getBeerByPage(page: Int): Result<List<Beer>>

    /**
     * Creates Page source by using Use case implementation call
     *
     * @param - onLoad allows to known load errors without depend on view events.
     * @return - ready [PagingSource]
     */
    fun getBeerPagingSource(
        onLoad: (Int, Result<List<Beer>>) -> Unit
    ): PagingSource<Int, Beer>
}