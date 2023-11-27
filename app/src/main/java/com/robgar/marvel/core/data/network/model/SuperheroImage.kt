package com.robgar.marvel.core.data.network.model

import com.google.gson.annotations.SerializedName

data class SuperheroImage (@SerializedName("path") val path: String, @SerializedName("extension") val extension: String)