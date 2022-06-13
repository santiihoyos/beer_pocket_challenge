package com.santiihoyos.beerspocket.ui.feature.beers.domain

import com.santiihoyos.beers.data.entity.DataError
import com.santiihoyos.beers.data.repository.BeerRepository
import com.santiihoyos.beerspocket.domain.entity.error.BeerError
import com.santiihoyos.beerspocket.domain.usecase.impl.GetPaginatedBeerImpl
import com.santiihoyos.beerspocket.ui.feature.beers.CharacterMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class GePaginatedCharactersUserCaseImplTest {

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test any getCharacters page`() = runTest {
        val repoMock = mock<BeerRepository>()
        whenever(repoMock.getBeersByPage(any(), any(), any())).thenReturn(
            Result.success(CharacterMocks.characterResponses)
        )
        val getPaginatedCharactersImpl = GetPaginatedBeerImpl(
            itemsPerPage = 3,
            characterRepository = repoMock
        )
        val result = getPaginatedCharactersImpl.getBeerByPage(1)
        assert(result.isSuccess && result.getOrNull()?.count() == CharacterMocks.page1.count())
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test failure when Repository fails`() = runTest {
        val repoMock = mock<BeerRepository>()
        whenever(repoMock.getBeersByPage(any(), any(), any())).thenReturn(
            Result.failure(DataError.NotFoundError)
        )
        val getPaginatedCharactersImpl = GetPaginatedBeerImpl(
            itemsPerPage = 3,
            characterRepository = repoMock
        )
        val result = getPaginatedCharactersImpl.getBeerByPage(1)
        assert(result.isFailure && result.exceptionOrNull() is BeerError.UnknownBeerError)
    }

    ///test more error codes here...
}