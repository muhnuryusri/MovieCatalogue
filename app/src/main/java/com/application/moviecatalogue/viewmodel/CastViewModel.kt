package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.data.source.remote.CatalogueRepository

class CastViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListMovieCast(movieCastId: Int) = catalogueRepository.getMovieCast(movieCastId)
    fun getListTvShowCast(tvShowCastId: Int) = catalogueRepository.getTvShowCast(tvShowCastId)
}