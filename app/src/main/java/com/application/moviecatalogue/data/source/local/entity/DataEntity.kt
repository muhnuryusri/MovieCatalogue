package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEntity(
    val id: Int?,
    val title: String?,
    val score: Double,
    val poster: String?
) : Parcelable