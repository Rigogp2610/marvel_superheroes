package com.robgar.marvel.core.data

import android.app.Application
import com.robgar.marvel.R
import com.robgar.marvel.core.data.network.MarvelService
import com.robgar.marvel.core.ui.superheroes.SuperheroesState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val application: Application,
    private val marvelService: MarvelService
) {
    suspend fun getSuperheroes() : SuperheroesState {
        try {
            val response = marvelService.getSuperheroes()
            val superheroes = response.body()?.data?.results
            if (superheroes != null && superheroes.isNotEmpty()) {
                return SuperheroesState.Success(superheroes)
            } else {
                return SuperheroesState.Error(application.getString(R.string.error_get_data))
            }
        } catch (e: Exception) {
            return SuperheroesState.Error(e.message ?: "")
        }
    }
}