package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.application.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.application.moviecatalogue.data.source.remote.CatalogueRepository
import com.application.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovie = DataDummy.getDetailMovie()
    private val movieId = dummyMovie.id

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieDetailEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowDetailEntity>

    @Before
    fun setUpMovie() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieDetailEntity>()
        movie.value = dummyMovie

        `when`(movieId?.let { catalogueRepository.getDetailMovie(it) }).thenReturn(movie)

        val movieData = movieId?.let { detailViewModel.getSelectedMovie(it).value } as MovieDetailEntity

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.title, movieData.title)
        assertEquals(dummyMovie.release, movieData.release)
        assertEquals(dummyMovie.duration, movieData.duration)
        assertEquals(dummyMovie.score, movieData.score)
        assertEquals(dummyMovie.genre, movieData.genre)
        assertEquals(dummyMovie.overview, movieData.overview)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.preview, movieData.preview)

        detailViewModel.getSelectedMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Before
    fun setUpTvShow() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShowDetailEntity>()
        tvShow.value = dummyTvShow

        `when`(tvShowId?.let { catalogueRepository.getDetailTvShow(it) }).thenReturn(tvShow)

        val tvShowData = tvShowId?.let { detailViewModel.getSelectedTvShow(it).value } as TvShowDetailEntity

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData.id)
        assertEquals(dummyTvShow.title, tvShowData.title)
        assertEquals(dummyTvShow.release, tvShowData.release)
        assertEquals(dummyTvShow.season, tvShowData.season)
        assertEquals(dummyTvShow.score, tvShowData.score)
        assertEquals(dummyTvShow.genre, tvShowData.genre)
        assertEquals(dummyTvShow.overview, tvShowData.overview)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.preview, tvShowData.preview)

        detailViewModel.getSelectedTvShow(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}