package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.data.source.remote.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListMovie() = catalogueRepository.getMovies()
}