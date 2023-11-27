package com.robgar.marvel.core.data.network.model

import com.google.gson.annotations.SerializedName

data class SuperheroApi (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: SuperheroImage,
    @SerializedName("comics") val comics: ComicList?,
    @SerializedName("series") val series: SeriesList?
)