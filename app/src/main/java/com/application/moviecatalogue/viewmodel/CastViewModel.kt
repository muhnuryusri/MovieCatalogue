package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.vo.Resource

class CastViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListMovieCast(movieCastId: Int): LiveData<Resource<PagedList<CastEntity>>> = catalogueRepository.getMovieCast(movieCastId)
    fun getListTvShowCast(tvShowCastId: Int): LiveData<Resource<PagedList<CastEntity>>> = catalogueRepository.getTvShowCast(tvShowCastId)
}