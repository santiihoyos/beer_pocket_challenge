package com.santiihoyos.beerspocket.ui.feature.list

import androidx.paging.PagingSource
import com.santiihoyos.beerspocket.domain.usecase.GetPaginatedBeersUseCase
import com.santiihoyos.beerspocket.domain.usecase.impl.BeerPagingSource
import com.santiihoyos.beerspocket.ui.feature.beers.CharacterMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class CharacterListViewModelTest {

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test init uiState is loading`() = runTest {
        val getUseCaseMock = mock<GetPaginatedBeersUseCase>()
        whenever(getUseCaseMock.getBeerByPage(any())).then {
            return@then Result.success(CharacterMocks.page1)
        }
        val viewModel = BeerListViewModel(getUseCaseMock)
        assert(
            viewModel.uiState.isLoading &&
                    viewModel.uiState.errorText == null &&
                    viewModel.uiState.beersListPager == null
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test uiState is loading with CharacterPagingSource`() = runTest {
        val getUseCaseMock = mock<GetPaginatedBeersUseCase>()
        whenever(getUseCaseMock.getBeerByPage(any())).then {
            return@then Result.success(CharacterMocks.page1)
        }
        val viewModel = BeerListViewModel(getUseCaseMock)
        whenever(getUseCaseMock.getBeerPagingSource(any())).then {
            runTest {
                BeerPagingSource(
                    getUseCaseMock::getBeerByPage,
                    viewModel::onLoadCharactersListener
                )
            }
        }
        viewModel.loadCharactersPager()
        assert(
            viewModel.uiState.beersListPager != null &&
                    viewModel.uiState.isLoading &&
                    viewModel.uiState.errorText == null
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test when load some page success isLoading is false`() = runTest {
        val getUseCaseMock = mock<GetPaginatedBeersUseCase>()
        val viewModel = BeerListViewModel(getUseCaseMock)
        whenever(getUseCaseMock.getBeerPagingSource(any())).then {
            runTest {
                BeerPagingSource(
                    { Result.success(CharacterMocks.page1) },
                    viewModel::onLoadCharactersListener
                )
            }
        }
        viewModel.loadCharactersPager()
        assert(
            viewModel.uiState.beersListPager != null && viewModel.uiState.isLoading
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test uiState is not loading after some load`() = runTest {
        val getUseCaseMock = mock<GetPaginatedBeersUseCase>()
        val viewModel = BeerListViewModel(getUseCaseMock)
        val pagingSource = BeerPagingSource(
            { Result.success(CharacterMocks.page1) },
            viewModel::onLoadCharactersListener
        )
        whenever(getUseCaseMock.getBeerPagingSource(any())).then { pagingSource }
        viewModel.loadCharactersPager()
        pagingSource.load(PagingSource.LoadParams.Refresh(key = 1, loadSize = 3, false))
        assert(
            !viewModel.uiState.isLoading && viewModel.uiState.errorText == null &&
                    viewModel.uiState.beersListPager != null
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `test uiState is not loading and there is errorText on fail load`() = runTest {
        val getUseCaseMock = mock<GetPaginatedBeersUseCase>()
        val viewModel = BeerListViewModel(getUseCaseMock)
        val pagingSource = BeerPagingSource(
            { Result.failure(Exception()) },
            viewModel::onLoadCharactersListener
        )
        whenever(getUseCaseMock.getBeerPagingSource(any())).then { pagingSource }
        viewModel.loadCharactersPager()
        pagingSource.load(PagingSource.LoadParams.Refresh(key = 1, loadSize = 3, false))
        assert(
            !viewModel.uiState.isLoading && viewModel.uiState.errorText != null &&
                    viewModel.uiState.beersListPager != null
        )
    }
}