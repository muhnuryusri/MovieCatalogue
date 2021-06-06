package com.application.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getMovieCast(movieCastId: Int): LiveData<Resource<List<CastEntity>>>
    fun getTvShowCast(tvShowCastId: Int): LiveData<Resource<List<CastEntity>>>
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>
    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}