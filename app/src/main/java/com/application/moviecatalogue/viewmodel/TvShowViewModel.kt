package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogueRepository.getTvShows()
}