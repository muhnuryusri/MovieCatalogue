package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cast_entities")
data class CastEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "cast_id")
        val id: Int?,

        @ColumnInfo(name = "name")
        val name: String?,

        @ColumnInfo(name = "character")
        val character: String?,

        @ColumnInfo(name = "photo")
        val photo: String?,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
) : Parcelable