package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.application.moviecatalogue.data.source.local.entity.DataEntity
import com.application.moviecatalogue.data.source.remote.CatalogueRepository
import com.application.moviecatalogue.utils.DataDummy
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
    private lateinit var observer: Observer<List<DataEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowCatalogueRepository)
    }

    @Test
    fun getListTvShows() {
        val dummyTvShow = DataDummy.getTvShows()
        val tvShows = MutableLiveData<List<DataEntity>>()
        tvShows.value = dummyTvShow

        `when`(tvShowCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getListTvShow().value
        verify(tvShowCatalogueRepository).getTvShows()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getListTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}