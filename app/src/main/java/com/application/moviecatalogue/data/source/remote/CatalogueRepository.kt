package com.application.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.data.source.local.entity.DataEntity
import com.application.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.application.moviecatalogue.data.source.remote.response.*
import com.application.moviecatalogue.data.source.remote.response.RemoteDataSource

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource{
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData)
            }
    }

    override fun getMovies(): LiveData<List<DataEntity>> {
        val movieItems = MutableLiveData<List<DataEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<MovieResultsItem>?) {
                val movieList = ArrayList<DataEntity>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = DataEntity(id, title, voteAverage, posterPath)
                            movieList.add(movie)
                        }
                    }
                    movieItems.postValue(movieList)
                }
            }
        })
        return movieItems
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieDetailEntity> {
        val movieDetailItems = MutableLiveData<MovieDetailEntity>()

        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = MovieDetailEntity(
                            id = id,
                            title = title,
                            release = releaseDate,
                            duration = runtime,
                            score = voteAverage,
                            genre = listGenres,
                            overview = overview,
                            poster = posterPath,
                            preview = backdropPath
                        )
                        movieDetailItems.postValue(detailMovie)
                    }
                }
            }
        }, movieId)
        return movieDetailItems
    }

    override fun getTvShows(): LiveData<List<DataEntity>> {
        val tvShowItems = MutableLiveData<List<DataEntity>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShowResultsItem>?) {
                val tvShowList = ArrayList<DataEntity>()
                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow = DataEntity(id, title, voteAverage, posterPath)
                            tvShowList.add(tvShow)
                        }
                    }
                    tvShowItems.postValue(tvShowList)
                }
            }
        })
        return tvShowItems
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvShowDetailEntity> {
        val tvShowDetailItems = MutableLiveData<TvShowDetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailTvShow = TvShowDetailEntity(
                            id = id,
                            title = name,
                            release = firsAirDate,
                            season = numberOfSeasons,
                            score = voteAverage,
                            genre = listGenres,
                            overview = overview,
                            poster = posterPath,
                            preview = backdropPath
                        )
                        tvShowDetailItems.postValue(detailTvShow)
                    }
                }
            }
        }, tvShowId)
        return tvShowDetailItems
    }

    override fun getMovieCast(movieCastId: Int): LiveData<List<CastEntity>> {
        val movieCastItems = MutableLiveData<List<CastEntity>>()

        remoteDataSource.getMovieCast(object : RemoteDataSource.LoadCastCallback {
            override fun onCastLoaded(casts: List<CastItem>?) {
                val movieCastList = ArrayList<CastEntity>()
                if (casts != null) {
                    for (response in casts) {
                        with(response) {
                            val movieCast = CastEntity(id, name, character, profilePath)
                            movieCastList.add(movieCast)
                        }
                    }
                    movieCastItems.postValue(movieCastList)
                }
            }
        }, movieCastId)
        return movieCastItems
    }

    override fun getTvShowCast(tvShowCastId: Int): LiveData<List<CastEntity>> {
        val tvShowCastItems = MutableLiveData<List<CastEntity>>()

        remoteDataSource.getTvShowCast(object : RemoteDataSource.LoadCastCallback {
            override fun onCastLoaded(casts: List<CastItem>?) {
                val tvShowCastList = ArrayList<CastEntity>()
                if (casts != null) {
                    for (response in casts) {
                        with(response) {
                            val movieCast = CastEntity(id, name, character, profilePath)
                            tvShowCastList.add(movieCast)
                        }
                    }
                    tvShowCastItems.postValue(tvShowCastList)
                }
            }
        }, tvShowCastId)
        return tvShowCastItems
    }
}