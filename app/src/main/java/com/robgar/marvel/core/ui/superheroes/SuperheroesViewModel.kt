package com.robgar.marvel.core.ui.superheroes

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.marvel.R
import com.robgar.marvel.core.usecase.GetSuperheroesUseCase
import com.robgar.marvel.core.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperheroesViewModel @Inject constructor(
    private val application: Application,
    private val getSuperheroesUseCase: GetSuperheroesUseCase
) : ViewModel() {

    private val _superheroes = MutableLiveData<SuperheroesState>()
    val superheroes : LiveData<SuperheroesState> = _superheroes

    fun getSuperheroes() {
        if (hasInternetConnection(application.applicationContext)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    _superheroes.postValue(SuperheroesState.Loading)

                    val superheroesState = getSuperheroesUseCase()

                    when (superheroesState) {
                        SuperheroesState.Loading -> {
                            _superheroes.postValue(SuperheroesState.Loading)
                        }
                        is SuperheroesState.Error -> {
                            _superheroes.postValue(SuperheroesState.Error(superheroesState.error))
                        }
                        is SuperheroesState.Success -> {
                            _superheroes.postValue(SuperheroesState.Success(superheroesState.superheroes))
                        }
                    }
                } catch (e: Exception) {
                    _superheroes.postValue(SuperheroesState.Error(e.message ?: application.getString(R.string.error_get_data)))
                }
            }
            _superheroes.postValue(SuperheroesState.Error(application.getString(R.string.error_internet)))
        }
    }
}