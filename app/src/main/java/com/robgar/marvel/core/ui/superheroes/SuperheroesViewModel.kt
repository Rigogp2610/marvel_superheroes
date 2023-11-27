package com.robgar.marvel.core.ui.superheroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.marvel.core.data.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperheroesViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
) : ViewModel() {

    fun getSuperheroes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val superheroes = marvelRepository.getSuperheroes()
            } catch (e: Exception) {
            }
        }
    }
}