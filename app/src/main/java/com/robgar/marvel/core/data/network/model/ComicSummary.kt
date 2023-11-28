package com.robgar.marvel.core.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicSummary(
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name")  val name: String?
) : Parcelable