package com.robgar.marvel.core.ui.superheroes

import com.robgar.marvel.core.data.network.model.Superhero

sealed class Action {
    object GetSuperheroes: Action()
}

fun Intent.mapToAction() : Action {
    return when (this) {
        Intent.GetSuperheroes -> Action.GetSuperheroes
    }
}