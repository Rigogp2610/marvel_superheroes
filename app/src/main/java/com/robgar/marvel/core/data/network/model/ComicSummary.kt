package com.robgar.marvel.core.data.network.model

import com.google.gson.annotations.SerializedName

data class ComicSummary(
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name")  val name: String?
)