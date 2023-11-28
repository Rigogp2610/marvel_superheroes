package com.robgar.marvel.core.ui.superhero_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroDetailScreen(navController: NavHostController, superhero: Superhero?) {
    Scaffold (
        topBar = {
            SuperheroeTopBar(superhero) {
                navController.navigateUp()
            }
        }
    ) {
        Column (
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SuperheroeTopBar(superhero: Superhero?, onBackScreen: () -> Unit) {
    TopAppBar(
        title = { Text(text = superhero?.name ?: "") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppColor,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onBackScreen() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.White)
            }
        }
    )
}