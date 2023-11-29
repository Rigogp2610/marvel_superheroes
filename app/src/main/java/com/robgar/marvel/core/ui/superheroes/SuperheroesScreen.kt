package com.robgar.marvel.core.ui.superheroes

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.ui.Routes
import com.robgar.marvel.core.ui.SUPERHEROE_DETAIL_ARGUMENT
import com.robgar.marvel.ui.theme.AppColor
import com.robgar.marvel.ui.theme.BorderStrokeColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesScreen(navController: NavHostController, viewModel: SuperheroesViewModel = hiltViewModel()) {
    val context = LocalContext.current
    //val superheroesState : SuperheroesState by viewModel.superheroes.observeAsState(initial = SuperheroesState.Loading)

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<UIState>(
        initialValue = UIState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect {
                value = it
            }
        }
    }

   LaunchedEffect(key1 = Unit, block = {
       viewModel.intentFlow.emit(Intent.GetSuperheroes)
   })

    Scaffold (
        topBar = { SuperheroesTopBar() }
    ) {
        Column (
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
            when (uiState) {
                UIState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }
                is UIState.Error -> {
                    Toast.makeText(
                        context,
                        (uiState as UIState.Error).error,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is UIState.Success -> {
                    SuperheroesList(
                        navController,
                        (uiState as UIState.Success).superheroes,
                        viewModel
                    )
                }
            }
        }
    }
    
}

@ExperimentalMaterial3Api
@Composable
fun SuperheroesTopBar() {
    TopAppBar(
        title = { Text(text = "Superheroes") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppColor,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun SuperheroesList(
    navController: NavHostController,
    superheroes: List<Superhero>,
    superheroesViewModel: SuperheroesViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        items(superheroes, key = { it.id }) { superhero ->
            Superhero(navController, superhero, superheroesViewModel)
        }
    }
}

@Composable
fun Superhero(navController: NavHostController, superhero: Superhero, superheroesViewModel: SuperheroesViewModel) {
    Card(
        border = BorderStroke(1.dp, BorderStrokeColor),
        colors = CardDefaults.cardColors(
            containerColor = BorderStrokeColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = SUPERHEROE_DETAIL_ARGUMENT,
                    value = superhero
                )
                navController.navigate(Routes.SuperheroeDetail.route)
            }
    ) {
        Column {
            AsyncImage(
                model = "${superhero.thumbnail.path}.${superhero.thumbnail.extension}",
                contentDescription = "Superhero image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(150.dp)
            )
            Text(text = superhero.name, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp), color = Color.Black)
        }
    }
}