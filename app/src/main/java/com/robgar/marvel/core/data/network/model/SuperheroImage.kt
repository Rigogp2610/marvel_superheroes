package com.robgar.marvel.core.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperheroImage(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
) : Parcelable