package com.robgar.marvel.core.ui.superheroes

import com.robgar.marvel.core.data.network.model.Superhero

sealed interface SuperheroesState {
    object Loading: SuperheroesState
    data class Error(val error: String): SuperheroesState
    data class Success(val superheroes: List<Superhero>): SuperheroesState
}