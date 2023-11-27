package com.robgar.marvel.core.ui.superheroes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SuperheroesScreen(
    viewModel: SuperheroesViewModel = hiltViewModel()
) {
    viewModel.getSuperheroes()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Text(text = "Pantalla 1", modifier = Modifier.align(Alignment.Center))
    }
}