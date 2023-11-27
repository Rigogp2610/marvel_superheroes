package com.robgar.marvel.core.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robgar.marvel.core.ui.superheroes.SuperheroesScreen

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    MarvelNavHost(navController)
}

@Composable
fun MarvelNavHost(navController: NavHostController) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = Routes.Superheroes.route) {
        composable(Routes.Superheroes.route) { SuperheroesScreen() }
    }
}