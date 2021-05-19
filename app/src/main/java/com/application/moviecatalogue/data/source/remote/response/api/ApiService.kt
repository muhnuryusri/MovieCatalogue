package com.application.moviecatalogue.data.source.remote.response.api

import com.application.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ) : Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<TvShowDetailResponse>

    @GET("movie/{id}/credits")
    fun getMovieCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<CastResponse>

    @GET("tv/{id}/credits")
    fun getTvShowCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<CastResponse>
}