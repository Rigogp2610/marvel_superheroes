package com.robgar.marvel.core.data.network.model

import com.google.gson.annotations.SerializedName

data class ComicList (@SerializedName("items") val items: List<ComicSummary>?)