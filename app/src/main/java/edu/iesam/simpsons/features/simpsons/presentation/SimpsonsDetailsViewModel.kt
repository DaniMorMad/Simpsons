package edu.iesam.simpsons.features.simpsons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.simpsons.features.simpsons.domain.ErrorApp
import edu.iesam.simpsons.features.simpsons.domain.Character
import edu.iesam.simpsons.features.simpsons.domain.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class SimpsonsDetailsViewModel(
    val getById: GetCharacterByIdUseCase
) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSimpson(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getById(id).fold({ onSuccess(it) }, { onError(it as ErrorApp) })
        }
    }


    private fun onSuccess(characters: Character) {
        _uiState.value = UiState(characters = characters)
    }


    private fun onError(error: ErrorApp) {
        _uiState.value = UiState(error = error)
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val characters: Character? = null
    )

}