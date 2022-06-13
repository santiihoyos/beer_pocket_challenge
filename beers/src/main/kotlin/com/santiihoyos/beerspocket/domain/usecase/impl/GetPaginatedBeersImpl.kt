package com.santiihoyos.beerspocket.domain.usecase.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santiihoyos.beers.data.repository.BeerRepository
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.domain.entity.error.BeerError
import com.santiihoyos.beerspocket.domain.extension.toCharacter
import com.santiihoyos.beerspocket.domain.usecase.GetPaginatedBeersUseCase

class GetPaginatedBeerImpl(
    override var itemsPerPage: Int = 20,
    private val beerRepository: BeerRepository
) : GetPaginatedBeersUseCase {

    override suspend fun getBeerByPage(page: Int): Result<List<Beer>> {
        val result = beerRepository.getBeersByPage(
            limit = itemsPerPage,
            offset = page * itemsPerPage,
            orderBy = "name"
        )
        return if (result.isSuccess && result.getOrNull() != null) {
            Result.success(
                result.getOrNull()!!.map {
                    it.toCharacter()
                }.toList()
            )
        } else {
            Result.failure(BeerError.UnknownBeerError)
        }
    }

    override fun getBeerPagingSource(
        onLoad: (Int, Result<List<Beer>>) -> Unit
    ): PagingSource<Int, Beer> {
        return BeerPagingSource(this::getBeerByPage, onLoad)
    }
}

/**
 * Paging Source.
 *
 * DISCLAIMER: Google says that we should put it into data layer but our data layer is android
 * libraries agnostic so we keep it here to allow reuse without forcing api module
 * accomplishment to android specific implementations.
 */
class BeerPagingSource(
    private val onNextPage: suspend (page: Int) -> Result<List<Beer>>,
    private val onLoad: (currentPage: Int, Result<List<Beer>>) -> Unit
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val nextPage = params.key ?: 1
        val beers = onNextPage(nextPage)
        onLoad(nextPage, beers)
        return if (beers.isFailure) {
            LoadResult.Error(
                beers.exceptionOrNull() ?: UnknownError()
            )
        } else {
            val newCharacters = beers.getOrNull()
            LoadResult.Page(
                data = newCharacters ?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (newCharacters.isNullOrEmpty()) null else nextPage + 1
            )
        }
    }
}