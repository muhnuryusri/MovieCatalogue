package com.application.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.vo.Resource

class FavoriteViewModel (private val catalogueRepository: CatalogueRepository): ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovie()
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavoriteTvShow()
}