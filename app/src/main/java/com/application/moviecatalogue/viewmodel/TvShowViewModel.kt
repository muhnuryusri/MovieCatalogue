package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.data.source.remote.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListTvShow() = catalogueRepository.getTvShows()
}