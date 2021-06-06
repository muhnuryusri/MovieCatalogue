package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.vo.Resource

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getSelectedMovie(movieId: Int): LiveData<Resource<MovieEntity>> = catalogueRepository.getDetailMovie(movieId)
    fun getSelectedTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> = catalogueRepository.getDetailTvShow(tvShowId)
}