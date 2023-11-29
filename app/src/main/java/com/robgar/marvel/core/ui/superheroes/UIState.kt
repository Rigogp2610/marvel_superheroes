package com.robgar.marvel.core.ui.superheroes

import com.robgar.marvel.core.data.network.model.Superhero

sealed interface UIState {
    object Loading: UIState
    data class Error(val error: String): UIState
    data class Success(val superheroes: List<Superhero>): UIState
}