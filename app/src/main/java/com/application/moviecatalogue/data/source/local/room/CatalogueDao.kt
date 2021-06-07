package com.application.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.application.moviecatalogue.data.source.local.entity.*

@Dao
interface CatalogueDao {
    @Query("SELECT * FROM movie_entities")
    fun getListMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities")
    fun getListTvShow(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getListFavoriteMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getListFavoriteTvShow(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId")
    fun getMovieDetailData(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId = :tvShowId")
    fun getTvShowDetailData(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM cast_entities WHERE cast_id = :castId")
    fun getCastData(castId: Int): LiveData<List<CastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(data: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(data: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CastEntity::class)
    fun insertCast(data: List<CastEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Update
    fun updateMovieData(movie: MovieEntity)

    @Update
    fun updateTvShowData(tvShow: TvShowEntity)
}