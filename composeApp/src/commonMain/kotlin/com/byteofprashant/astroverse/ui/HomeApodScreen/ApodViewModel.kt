package com.byteofprashant.astroverse.ui.HomeApodScreen

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byteofprashant.domain.model.Apod
import com.byteofprashant.domain.usesCases.GetApodUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import toUiState

class ApodViewModel(private val getApodUseCase: GetApodUseCase) : ViewModel() {

    private var _apodUiState = MutableStateFlow<UiState<Apod>>(UiState.Loading)
    val apodUiState: StateFlow<UiState<Apod>> = _apodUiState.asStateFlow()

    fun getApod(date: String? = null) {
        viewModelScope.launch {
            _apodUiState.value = UiState.Loading
            val result = getApodUseCase.invoke(date)
            _apodUiState.value = result.toUiState()
        }
    }

}