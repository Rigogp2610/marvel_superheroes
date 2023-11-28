package com.robgar.marvel.core.ui

import com.robgar.marvel.core.data.network.model.Superhero

const val SUPERHEROES_ROUTE = "superheroes"
const val SUPERHEROE_DETAIL_ROUTE = "superhero"
const val SUPERHEROE_DETAIL_ARGUMENT = "superhero"

sealed class Routes(val route: String) {
    object Superheroes : Routes(SUPERHEROES_ROUTE)
    object SuperheroeDetail : Routes(SUPERHEROE_DETAIL_ROUTE)
}