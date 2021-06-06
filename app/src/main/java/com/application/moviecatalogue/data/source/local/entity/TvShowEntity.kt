package com.application.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv_show_entities")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tvShowId")
        val id: Int?,

        @ColumnInfo(name = "title")
        val title: String?,

        @ColumnInfo(name = "release")
        val release: String?,

        @ColumnInfo(name = "season")
        val season: String?,

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