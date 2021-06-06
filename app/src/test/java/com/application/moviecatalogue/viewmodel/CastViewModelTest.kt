package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CastViewModelTest {
    private lateinit var castViewModel: CastViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var castCatalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<CastEntity>>

    @Before
    fun setUp() {
        castViewModel = CastViewModel(castCatalogueRepository)
    }

    @Test
    fun getListMoviesCast() {
        val dummyMovieCast = DataDummy.getMovieCast()
        val movieCast = MutableLiveData<List<CastEntity>>()
        movieCast.value = dummyMovieCast

        `when`(castCatalogueRepository.getMovieCast(anyInt())).thenReturn(movieCast)
        val movie = castViewModel.getListMovieCast(anyInt()).value
        verify(castCatalogueRepository).getMovieCast(anyInt())
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        castViewModel.getListMovieCast(anyInt()).observeForever(observer)
        verify(observer).onChanged(dummyMovieCast)
    }

    @Test
    fun getListTvShowsCast() {
        val dummyTvShowCast = DataDummy.getTvShowCast()
        val tvShowCast = MutableLiveData<List<CastEntity>>()
        tvShowCast.value = dummyTvShowCast

        `when`(castCatalogueRepository.getTvShowCast(anyInt())).thenReturn(tvShowCast)
        val tvShow = castViewModel.getListTvShowCast(anyInt()).value
        verify(castCatalogueRepository).getTvShowCast(anyInt())
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        castViewModel.getListTvShowCast(anyInt()).observeForever(observer)
        verify(observer).onChanged(dummyTvShowCast)
    }
}