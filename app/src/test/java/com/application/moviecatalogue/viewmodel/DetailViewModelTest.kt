package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
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
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovie = DataDummy.getDummyDetailMovie()
    private val movieId = dummyMovie.id

    private val dummyTvShow = DataDummy.getDummyDetailTvShow()
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUpMovie() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieId?.let { catalogueRepository.getDetailMovie(it) }).thenReturn(movie)

        if (movieId != null) {
            detailViewModel.getSelectedMovie(movieId).observeForever(movieObserver)
        }
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Before
    fun setUpTvShow() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(tvShowId?.let { catalogueRepository.getDetailTvShow(it) }).thenReturn(tvShow)

        if (tvShowId != null) {
            detailViewModel.getSelectedTvShow(tvShowId).observeForever(tvShowObserver)
        }
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }
}