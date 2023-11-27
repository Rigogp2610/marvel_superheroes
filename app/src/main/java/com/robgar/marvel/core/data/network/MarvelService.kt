package com.robgar.marvel.core.data.network

import javax.inject.Inject

class MarvelService @Inject constructor(
    private val marvelClient: MarvelClient
) {
    suspend fun getSuperheroes() = marvelClient.getSuperheroes()
}