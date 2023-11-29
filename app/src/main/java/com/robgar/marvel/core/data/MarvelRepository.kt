package com.robgar.marvel.core.data

import android.app.Application
import com.robgar.marvel.R
import com.robgar.marvel.core.data.network.MarvelService
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.data.network.model.SuperheroImage
import com.robgar.marvel.core.ui.superheroes.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val application: Application,
    private val marvelService: MarvelService
) {
    suspend fun getSuperheroes() : Flow<List<Superhero>> {
        try {
            val response = marvelService.getSuperheroes()
            val superheroes = response.body()?.data?.results
            if (superheroes != null && superheroes.isNotEmpty()) {
                return flowOf(superheroes
                    .filter { !it.thumbnail.path.contains("image_not_available") }
                    .map { superhero ->
                        superhero.copy(
                            thumbnail = SuperheroImage(
                                superhero.thumbnail.path.replace(
                                    "http",
                                    "https"
                                ), superhero.thumbnail.extension
                            )
                        )
                    })
            } else {
                return flowOf(listOf())
            }
        } catch (e: Exception) {
            return flowOf(listOf())
        }
    }
}