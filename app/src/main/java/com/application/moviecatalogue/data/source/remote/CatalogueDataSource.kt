package com.application.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.data.source.local.entity.DataEntity
import com.application.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowDetailEntity

interface CatalogueDataSource {
    fun getMovies(): LiveData<List<DataEntity>>
    fun getDetailMovie(movieId: Int): LiveData<MovieDetailEntity>
    fun getTvShows(): LiveData<List<DataEntity>>
    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowDetailEntity>
    fun getMovieCast(movieCastId: Int): LiveData<List<CastEntity>>
    fun getTvShowCast(tvShowCastId: Int): LiveData<List<CastEntity>>
}