package com.application.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getListMovie(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getListMovie()

    fun getListTvShow(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getListTvShow()

    fun getListFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getListFavoriteMovie()

    fun getListFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getListFavoriteTvShow()

    fun getMovieDetailData(movieId: Int): LiveData<MovieEntity> = mCatalogueDao.getMovieDetailData(movieId)

    fun getTvShowDetailData(tvShowId: Int): LiveData<TvShowEntity> = mCatalogueDao.getTvShowDetailData(tvShowId)

    fun getCast(): DataSource.Factory<Int, CastEntity> = mCatalogueDao.getCast()

    fun insertMovie(movieList: List<MovieEntity>) = mCatalogueDao.insertMovie(movieList)

    fun insertTvShow(tvShowList: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShowList)

    fun insertMovieCast(movieCastList: List<CastEntity>) = mCatalogueDao.insertMovieCast(movieCastList)

    fun insertTvShowCast(tvShowMovieCastList: List<CastEntity>) = mCatalogueDao.insertTvShowCast(tvShowMovieCastList)

    fun updateMovieData(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovieData(movie)
    }

    fun updateTvShowData(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTvShowData(tvShow)
    }

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTvShow(tvShow)
    }
}