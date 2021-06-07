package com.application.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.application.moviecatalogue.data.source.local.LocalDataSource
import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.data.source.remote.ApiResponse
import com.application.moviecatalogue.data.source.remote.RemoteDataSource
import com.application.moviecatalogue.data.source.remote.response.*
import com.application.moviecatalogue.utils.AppExecutors
import com.application.moviecatalogue.vo.Resource
import java.lang.StringBuilder

class CatalogueRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) :
    CatalogueDataSource {
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogueRepository =
                instance ?: synchronized(this) {
                    instance ?: CatalogueRepository(remoteData, localData, appExecutors).apply {
                        instance = this
                    }
                }
    }

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResultsItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                    localDataSource.getListMovie()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                    remoteDataSource.getMovies()

            public override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            "",
                            0,
                            response.voteAverage,
                            "",
                            "",
                            response.posterPath,
                            ""
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                    localDataSource.getMovieDetailData(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data != null && data.duration == 0 && data.genre == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                    remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val listGenres = StringBuilder().append("")

                for (genre in data.genres.indices) {
                    if (genre < data.genres.size - 1) {
                        listGenres.append("${data.genres[genre].name}, ")
                    } else {
                        listGenres.append(data.genres[genre].name)
                    }
                }

                val detailMovie = MovieEntity(
                        id = data.id,
                        title = data.title,
                        release = data.releaseDate,
                        duration = data.runtime,
                        score = data.voteAverage,
                        genre = listGenres.toString(),
                        overview = data.overview,
                        poster = data.posterPath,
                        preview = data.backdropPath,
                        isFavorite = false
                )
                localDataSource.updateMovieData(detailMovie, false)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                    localDataSource.getListTvShow()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                    remoteDataSource.getTvShows()

            public override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                            response.id,
                            response.title,
                            "",
                            "",
                            response.voteAverage,
                            "",
                            "",
                            response.posterPath,
                            ""
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                    localDataSource.getTvShowDetailData(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                    data?.genre != null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                    remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                val listGenres = StringBuilder().append("")

                for (genre in data.genres.indices) {
                    if (genre < data.genres.size - 1) {
                        listGenres.append("${data.genres[genre].name}, ")
                    } else {
                        listGenres.append(data.genres[genre].name)
                    }
                }

                val detailTvShow = TvShowEntity(
                        id = data.id,
                        title = data.name,
                        release = data.firsAirDate,
                        season = data.numberOfSeasons,
                        score = data.voteAverage,
                        genre = listGenres.toString(),
                        overview = data.overview,
                        poster = data.posterPath,
                        preview = data.backdropPath
                )
                localDataSource.updateTvShowData(detailTvShow, false)
            }
        }.asLiveData()
    }

    override fun getMovieCast(movieCastId: Int): LiveData<Resource<List<CastEntity>>> {
        return object : NetworkBoundResource<List<CastEntity>, List<CastItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<CastEntity>> =
                    localDataSource.getCastData(movieCastId)

            override fun shouldFetch(data: List<CastEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<CastItem>>> =
                    remoteDataSource.getMovieCast(movieCastId)

            public override fun saveCallResult(data: List<CastItem>) {
                val movieCastList = ArrayList<CastEntity>()
                for (response in data) {
                    val movieCast = CastEntity(
                            response.id,
                            response.name,
                            response.character,
                            response.profilePath
                    )
                    movieCastList.add(movieCast)
                }
                localDataSource.insertCast(movieCastList)
            }
        }.asLiveData()
    }

    override fun getTvShowCast(tvShowCastId: Int): LiveData<Resource<List<CastEntity>>> {
        return object : NetworkBoundResource<List<CastEntity>, List<CastItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<CastEntity>> =
                    localDataSource.getCastData(tvShowCastId)

            override fun shouldFetch(data: List<CastEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<CastItem>>> =
                    remoteDataSource.getTvShowCast(tvShowCastId)

            public override fun saveCallResult(data: List<CastItem>) {
                val tvShowCastList = ArrayList<CastEntity>()
                for (response in data) {
                    val tvShowCast = CastEntity(
                            response.id,
                            response.name,
                            response.character,
                            response.profilePath
                    )
                    tvShowCastList.add(tvShowCast)
                }
                localDataSource.insertCast(tvShowCastList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<List<MovieEntity>> =
            localDataSource.getListFavoriteMovie()

    override fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> =
        localDataSource.getListFavoriteTvShow()

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}