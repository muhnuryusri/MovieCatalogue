package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.utils.DataDummy
import com.application.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowCatalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowCatalogueRepository)
    }

    @Test
    fun getListTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(tvShowCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val movie = viewModel.getListTvShow().value?.data
        verify(tvShowCatalogueRepository).getTvShows()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getListTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}