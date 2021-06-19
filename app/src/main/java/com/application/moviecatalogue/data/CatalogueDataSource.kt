package com.application.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getMovieCast(movieCastId: Int): LiveData<Resource<PagedList<CastEntity>>>
    fun getTvShowCast(tvShowCastId: Int): LiveData<Resource<PagedList<CastEntity>>>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}