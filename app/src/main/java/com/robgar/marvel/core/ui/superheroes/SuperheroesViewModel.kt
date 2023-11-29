package com.robgar.marvel.core.ui.superheroes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.marvel.R
import com.robgar.marvel.core.ui.superheroes.UIState.Success
import com.robgar.marvel.core.usecase.GetSuperheroesUseCase
import com.robgar.marvel.core.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperheroesViewModel @Inject constructor(
    private val application: Application,
    private val getSuperheroesUseCase: GetSuperheroesUseCase
) : ViewModel() {

    val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState>
        get() = _uiState

    val intentFlow = MutableSharedFlow<Intent>()

    init {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                processAction(intent.mapToAction())
            }
        }
    }

    private suspend fun processAction(action: Action) {
        return when (action) {
            Action.GetSuperheroes -> getSuperheroes()
        }
    }

    private suspend fun getSuperheroes() {
        if (hasInternetConnection(application.applicationContext)) {
            _uiState.value = UIState.Loading
            getSuperheroesUseCase().catch { error ->
                _uiState.emit(UIState.Error(error.message ?: application.getString(R.string.error_get_data)))
            }.collect { superheroes ->
                _uiState.value = Success(superheroes)
            }
        } else {
            _uiState.value = UIState.Error(application.getString(R.string.error_internet))
        }
    }
}