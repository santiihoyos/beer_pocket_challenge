package com.santiihoyos.beers.data.repository

import com.santiihoyos.beers.data.Mocks
import com.santiihoyos.beers.data.datasource.impl.RestBeersDataSource
import com.santiihoyos.beers.data.entity.DataError
import com.santiihoyos.beers.data.repository.impl.BeerRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class) //android libraries...
class BeerRepositoryImplTest {

    @Test
    fun `test getCharactersByPage was ok`() = runTest {
        val restDataSourceMock = mock<RestBeersDataSource>()
        whenever(restDataSourceMock.getBeers(any(), any() )).thenReturn(
            Mocks.response200
        )
        val repo = BeerRepositoryImpl(restDataSourceMock, mock())
        val result = repo.getBeersByPage(1, 3)
        assert(
            result.isSuccess && (result.getOrNull()?.count()
                ?: 0) == Mocks.response200.count() &&
                    result.getOrNull()?.get(0) == Mocks.response200[0]
        )
    }

    @Test
    fun `test getCharactersByPage was ko with DataError_Unknown on another Exception`() = runTest {
        val restDataSourceMock = mock<RestBeersDataSource>()
        whenever(restDataSourceMock.getBeers(any(), any(), )).thenAnswer {
            throw Exception()
        }
        val repo = BeerRepositoryImpl(restDataSourceMock, mock())
        val result = repo.getBeersByPage(1, 3)
        assert(
            result.isFailure && result.exceptionOrNull() is DataError.UnknownError
        )
    }

}