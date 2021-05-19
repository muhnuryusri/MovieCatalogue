package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.application.moviecatalogue.data.source.remote.CatalogueRepository

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getSelectedMovie(movieId: Int): LiveData<MovieDetailEntity> = catalogueRepository.getDetailMovie(movieId)
    fun getSelectedTvShow(tvShowId: Int): LiveData<TvShowDetailEntity> = catalogueRepository.getDetailTvShow(tvShowId)
}