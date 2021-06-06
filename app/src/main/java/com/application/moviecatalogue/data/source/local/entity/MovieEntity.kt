package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_entities")
data class MovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        val id: Int?,

        @ColumnInfo(name = "title")
        val title: String?,

        @ColumnInfo(name = "release")
        val release: String?,

        @ColumnInfo(name = "duration")
        val duration: Int?,

        @ColumnInfo(name = "score")
        val score: Double?,

        @ColumnInfo(name = "genre")
        val genre: String?,

        @ColumnInfo(name = "overview")
        val overview: String?,

        @ColumnInfo(name = "poster")
        val poster: String?,

        @ColumnInfo(name = "preview")
        val preview: String?,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
) : Parcelable