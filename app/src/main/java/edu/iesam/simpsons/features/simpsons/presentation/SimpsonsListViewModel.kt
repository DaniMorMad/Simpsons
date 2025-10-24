package edu.iesam.simpsons.features.simpsons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.simpsons.features.simpsons.domain.ErrorApp
import edu.iesam.simpsons.features.simpsons.domain.GetAllCharactersUseCase
import edu.iesam.simpsons.features.simpsons.domain.Character
import edu.iesam.simpsons.features.simpsons.domain.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class SimpsonsListViewModel(
    val getAll: GetAllCharactersUseCase,
    val getById: GetCharacterByIdUseCase
) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadAllSimpsons() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getAll().fold({ onSuccessAll(it) }, { onError(it as ErrorApp) })
        }
    }

    fun loadSimpsonById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getById(id).fold({ onSuccessById(it) }, { onError(it as ErrorApp) })
        }
    }

    private fun onSuccessAll(characters: List<Character>) {
        _uiState.value = UiState(characters = characters)
    }

    private fun onSuccessById(character: Character) {
        _uiState.value = UiState(characters = listOf(character))
    }

    private fun onError(error: ErrorApp) {
        _uiState.value = UiState(error = error)
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val characters: List<Character>? = null
    )

}