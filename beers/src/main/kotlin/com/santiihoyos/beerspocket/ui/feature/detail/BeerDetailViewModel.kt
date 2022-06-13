package com.santiihoyos.beerspocket.ui.feature.detail

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.domain.usecase.GetBeerByIdUseCase
import com.santiihoyos.beerspocket.ui.feature.beers.R
import kotlinx.coroutines.launch

/**
 * UI state for BeerDetail view.
 */
data class BeerDetailUiState(

    /**
     * View should to show some loading status
     */
    val isLoading: Boolean = false,

    /**
     * View should show Beer information
     */
    val beer: Beer? = null,

    /**
     * String Res id to show on view when something went wrong
     */
    @StringRes val errorText: Int? = null
)

/**
 * BeerDetailViewModel state
 */
data class BeerDetailViewModelState(

    /**
     * Id of Beer that view requires.
     */
    val beerId: String? = null,
)

/**
 * ViewModel class for Beer detail views.
 */
class BeerDetailViewModel(
    private val getBeerById: GetBeerByIdUseCase
) : ViewModel() {

    /**
     * current state that View should show.
     */
    var uiState by mutableStateOf(BeerDetailUiState(isLoading = true))
        private set

    fun getBeerById(beerId: String?) = viewModelScope.launch {
        if (beerId.isNullOrEmpty()) {
            uiState = uiState.copy(isLoading = false, errorText = R.string.error_loading_beer)
        }
        uiState = uiState.copy(isLoading = true, errorText = null, beer = null)
        val resultBeer = getBeerById.getBeerById(beerId!!)
        uiState = if (resultBeer.isSuccess) {
            uiState.copy(
                isLoading = false,
                errorText = null,
                beer = resultBeer.getOrNull()
            )
        } else {
            uiState.copy(isLoading = false, errorText = R.string.error_loading_beer)
        }
    }
}