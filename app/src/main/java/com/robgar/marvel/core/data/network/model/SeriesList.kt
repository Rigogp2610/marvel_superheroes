package com.robgar.marvel.core.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesList(val items: List<SeriesSummary>?) : Parcelable