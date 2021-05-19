package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowDetailEntity(
    val id: Int?,
    val title: String?,
    val release: String?,
    val season: String?,
    val score: Double?,
    val genre: List<String>?,
    val overview: String?,
    val poster: String?,
    val preview: String?
) : Parcelable