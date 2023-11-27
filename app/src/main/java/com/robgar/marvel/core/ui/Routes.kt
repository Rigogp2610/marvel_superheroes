package com.robgar.marvel.core.ui

sealed class Routes(val route: String) {
    object Superheroes : Routes("superheroes")
}