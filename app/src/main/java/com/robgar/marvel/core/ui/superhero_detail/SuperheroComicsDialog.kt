package com.robgar.marvel.core.ui.superhero_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.robgar.marvel.core.data.network.model.ComicSummary
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.ui.theme.BorderStrokeColor

@Composable
fun SuperheroComicsDialog(superhero: Superhero, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(superhero.comics?.items!!) {
                    ComicItem(comic = it)
                }
            }
        }
    }
}

@Composable
fun ComicItem(comic: ComicSummary) {
    Card(
        border = BorderStroke(1.dp, BorderStrokeColor),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = comic.name ?: "",
            modifier = Modifier
                .padding(8.dp),
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }

}