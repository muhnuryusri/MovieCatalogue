package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailEntity(
    val id: Int?,
    val title: String?,
    val release: String?,
    val duration: Int,
    val score: Double?,
    val genre: List<String>?,
    val overview: String?,
    val poster: String?,
    val preview: String?
) : Parcelable