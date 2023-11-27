package com.robgar.marvel.core.data

import com.robgar.marvel.core.data.network.MarvelService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val marvelService: MarvelService
) {
    suspend fun getSuperheroes() = marvelService.getSuperheroes()
}