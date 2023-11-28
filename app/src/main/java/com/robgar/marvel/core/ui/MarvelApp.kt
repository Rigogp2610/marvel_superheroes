package com.robgar.marvel.core.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.ui.superhero_detail.SuperheroDetailScreen
import com.robgar.marvel.core.ui.superheroes.SuperheroesScreen

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    MarvelNavHost(navController)
}

@Composable
fun MarvelNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Superheroes.route) {
        composable(Routes.Superheroes.route) { SuperheroesScreen(navController) }
        composable(Routes.SuperheroeDetail.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Superhero>(SUPERHEROE_DETAIL_ARGUMENT)
            SuperheroDetailScreen(navController = navController, superhero = result)
        }
    }
}