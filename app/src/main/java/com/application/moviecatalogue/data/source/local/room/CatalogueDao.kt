package com.application.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.application.moviecatalogue.data.source.local.entity.*

@Dao
interface CatalogueDao {
    @Query("SELECT * FROM movie_entities")
    fun getListMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities")
    fun getListTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getListFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getListFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId")
    fun getMovieDetailData(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId = :tvShowId")
    fun getTvShowDetailData(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM cast_entities")
    fun getCast(): DataSource.Factory<Int, CastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(data: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(data: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CastEntity::class)
    fun insertMovieCast(data: List<CastEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CastEntity::class)
    fun insertTvShowCast(data: List<CastEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Update
    fun updateCast(cast: CastEntity)

    @Update
    fun updateMovieData(movie: MovieEntity)

    @Update
    fun updateTvShowData(tvShow: TvShowEntity)
}