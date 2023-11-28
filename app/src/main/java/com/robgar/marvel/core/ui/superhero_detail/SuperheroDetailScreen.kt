package com.robgar.marvel.core.ui.superhero_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.ui.theme.AppColor
import com.robgar.marvel.ui.theme.BorderStrokeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroDetailScreen(navController: NavHostController, superhero: Superhero?) {
    Scaffold(
        topBar = {
            SuperheroeTopBar(superhero) {
                navController.navigateUp()
            }
        }
    ) {
        if (superhero != null) SuperheroContent(superhero = superhero, paddingValues = it)
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
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SuperheroContent(superhero: Superhero, paddingValues: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
    ) {
        Card(
            border = BorderStroke(1.dp, BorderStrokeColor),
            colors = CardDefaults.cardColors(
                containerColor = BorderStrokeColor,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "${superhero.thumbnail.path}.${superhero.thumbnail.extension}",
                contentDescription = "Superhero image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(300.dp)
            )
        }
        Text(
            text = superhero.description,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        BottomButtons(superhero = superhero)
    }
}

@Composable
fun BottomButtons(superhero: Superhero, superheroViewModel: SuperheroViewModel = hiltViewModel()) {

    val showComicDialog : Boolean by superheroViewModel.showComicDialog.observeAsState(initial = false)
    val showSerieDialog : Boolean by superheroViewModel.showSerieDialog.observeAsState(initial = false)

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {

        val (comics, series) = createRefs()

        Button(
            modifier = Modifier
                .width(150.dp)
                .constrainAs(comics) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(series.start)
                },
            colors = ButtonDefaults.buttonColors(containerColor = AppColor),
            enabled = superhero.comics?.items != null && superhero.comics.items.isNotEmpty(),
            onClick = { superheroViewModel.onShowComicDialogClick() }) {
            Text(
                text = "Comics",
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        if (showComicDialog) SuperheroComicsDialog(superhero = superhero) { superheroViewModel.onComicDialogClose() }
        Button(
            modifier = Modifier
                .width(150.dp)
                .constrainAs(series) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(comics.end)
                },
            colors = ButtonDefaults.buttonColors(containerColor = AppColor),
            enabled = superhero.series?.items != null && superhero.series.items.isNotEmpty(),
            onClick = { superheroViewModel.onShowSerieDialogClick() }) {
            Text(
                text = "Series",
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        if (showSerieDialog) SuperheroSeriesDialog(superhero = superhero) { superheroViewModel.onSerieDialogClose() }

        createHorizontalChain(comics, series, chainStyle = ChainStyle.Spread)
    }
}