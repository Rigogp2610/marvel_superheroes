package com.robgar.marvel.core.data.network

import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.utils.PaginatedEnvelope
import retrofit2.Response
import retrofit2.http.GET

interface MarvelClient {

    @GET("/v1/public/characters?limit=40")
    suspend fun getSuperheroes() : Response<PaginatedEnvelope<Superhero>>
}