package com.robgar.marvel.core.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Superhero (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: SuperheroImage,
    @SerializedName("comics") val comics: ComicList?,
    @SerializedName("series") val series: SeriesList?
) : Parcelable