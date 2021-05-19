package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastEntity(
    val id: Int?,
    val name: String?,
    val character: String?,
    val photo: String?
) : Parcelable