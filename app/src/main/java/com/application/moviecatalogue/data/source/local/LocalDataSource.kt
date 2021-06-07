package com.application.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getListMovie(): LiveData<List<MovieEntity>> = mCatalogueDao.getListMovie()

    fun getListTvShow(): LiveData<List<TvShowEntity>> = mCatalogueDao.getListTvShow()

    fun getListFavoriteMovie(): LiveData<List<MovieEntity>> = mCatalogueDao.getListFavoriteMovie()

    fun getListFavoriteTvShow(): LiveData<List<TvShowEntity>> = mCatalogueDao.getListFavoriteTvShow()

    fun getMovieDetailData(movieId: Int): LiveData<MovieEntity> = mCatalogueDao.getMovieDetailData(movieId)

    fun getTvShowDetailData(tvShowId: Int): LiveData<TvShowEntity> = mCatalogueDao.getTvShowDetailData(tvShowId)

    fun getCastData(castId: Int): LiveData<List<CastEntity>> = mCatalogueDao.getCastData(castId)

    fun insertMovie(movieList: List<MovieEntity>) = mCatalogueDao.insertMovie(movieList)

    fun insertTvShow(tvShowList: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShowList)

    fun insertCast(castId: List<CastEntity>) = mCatalogueDao.insertCast(castId)

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