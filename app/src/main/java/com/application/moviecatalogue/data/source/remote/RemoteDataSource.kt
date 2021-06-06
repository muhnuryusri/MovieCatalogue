package com.application.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.moviecatalogue.BuildConfig.API_KEY
import com.application.moviecatalogue.data.source.remote.response.*
import com.application.moviecatalogue.data.source.remote.response.api.ApiConfig
import com.application.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        val client = ApiConfig.getApiService().getMovies(API_KEY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<MovieResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                resultMovies.value = ApiResponse.success(response.body() as MovieDetailResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                resultTvShows.value = ApiResponse.success(response.body()?.results as List<TvShowResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getDetailTvShow(tvShowId: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        val client = ApiConfig.getApiService().getTvShowDetail(tvShowId, API_KEY)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                resultTvShows.value = ApiResponse.success(response.body() as TvShowDetailResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getMovieCast(movieCastId: Int): LiveData<ApiResponse<List<CastItem>>> {
        EspressoIdlingResource.increment()
        val resultMovieCast = MutableLiveData<ApiResponse<List<CastItem>>>()
        val client = ApiConfig.getApiService().getMovieCast(movieCastId, API_KEY)
        client.enqueue(object : Callback<CastResponse> {
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                resultMovieCast.value = ApiResponse.success(response.body()?.cast as List<CastItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieCast onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovieCast
    }

    fun getTvShowCast(tvShowCastId: Int): LiveData<ApiResponse<List<CastItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShowCast = MutableLiveData<ApiResponse<List<CastItem>>>()
        val client = ApiConfig.getApiService().getTvShowCast(tvShowCastId, API_KEY)
        client.enqueue(object : Callback<CastResponse> {
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                resultTvShowCast.value = ApiResponse.success(response.body()?.cast as List<CastItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShowCast
    }
}