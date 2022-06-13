package com.santiihoyos.beerspocket.ui.feature.list

import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.domain.usecase.GetPaginatedBeersUseCase
import com.santiihoyos.beerspocket.ui.feature.beers.R
import kotlinx.coroutines.flow.Flow

/**
 * State that UI for View
 */
data class BeerListUiState(

    /**
     * If this it is not null View should show to user
     * error String resource
     */
    @StringRes
    val errorText: Int? = null,

    /**
     * Indicates viewModel is processing some task
     * on init is true
     */
    val isLoading: Boolean = false,

    /**
     * [Pager] to allows view loads/show paginated [Beer] [List]
     */
    val beersListPager: Flow<PagingData<Beer>>? = null
)

class BeerListViewModel(
    private val getPaginatedBeers: GetPaginatedBeersUseCase,
) : ViewModel() {

    /**
     * current state that View should show.
     */
    var uiState by mutableStateOf(BeerListUiState(isLoading = true))
        private set

    /**
     * ensure page limits and initialize listening to [this.viewModelState].
     */
    init {
        getPaginatedBeers.itemsPerPage = 20
    }

    fun loadBeersPager() {
        uiState = uiState.copy(
            isLoading = true,
            errorText = null,
            beersListPager = Pager(
                initialKey = 1,
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 2
                ),
                pagingSourceFactory = {
                    getPaginatedBeers.getBeerPagingSource(::onLoadBeersListener)
                },
            ).flow.cachedIn(viewModelScope)
        )
    }

    /**
     * Manage errors on Pager.
     *
     * @param result- last result on pager.
     */
    @VisibleForTesting
    fun onLoadBeersListener(currentPage: Int, result: Result<List<Beer>>) {
        if (currentPage >= 0 && result.isSuccess) {
            uiState = uiState.copy(isLoading = false)
        } else if(result.isFailure) {
            uiState = uiState.copy(isLoading = false, errorText = R.string.error_loading_beers)
        }
    }
}