package com.application.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.data.source.remote.CatalogueRepository
import com.application.moviecatalogue.data.source.remote.response.CastItem
import com.application.moviecatalogue.data.source.remote.response.CastResponse
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
class CastViewModelTest {
    private lateinit var castViewModel: CastViewModel

    private val dummyMovieCast = DataDummy.getMovieCast()
    private val movieCastId = dummyMovieCast.id

    private val dummyTvShowCast = DataDummy.getTvShowCast()
    private val tvShowCastId = dummyTvShowCast.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var castCatalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<CastDataEntity>

    @Before
    fun setUp() {
        castViewModel = CastViewModel(castCatalogueRepository)
    }

    @Test
    fun getListMovieCast() {
        val movieCastData = MutableLiveData<CastDataEntity>()
        val movieCastDataReturn = MutableLiveData<ArrayList<CastItem>>()
        movieCastData.value = dummyMovieCast

        `when`(movieCastId?.let { castCatalogueRepository.getMovieCast(it) }).thenReturn(movieCastData)
        val movie = movieCastId?.let { castViewModel.getListMovieCast(it).value }
        if (movieCastId != null) {
            verify(castCatalogueRepository).getMovieCast(movieCastId)
        }
        assertEquals(1, movie?.size)

        if (movieCastId != null) {
            castViewModel.getListMovieCast(movieCastId).observeForever(observer)
        }
        verify(observer).onChanged(listOf(dummyMovieCast))
    }

    @Test
    fun getListTvShowCast() {
        val tvShowCastData = MutableLiveData<CastResponse>()
        val tvShowCastDataReturn = MutableLiveData<List<CastEntity>>()
        tvShowCastData.value = dummyTvShowCast

        `when`(tvShowCastId.let { castCatalogueRepository.getTvShowCast(it) }).thenReturn(tvShowCastDataReturn)
        val tvShow = tvShowCastId.let { castViewModel.getListTvShowCast(it).value } as CastResponse
        verify(castCatalogueRepository).getTvShowCast(tvShowCastId)
        assertNotNull(tvShow)
        assertEquals(dummyTvShowCast.id, tvShow.id)
        assertEquals(dummyTvShowCast.cast, tvShow.cast)

        castViewModel.getListMovieCast(tvShowCastId).observeForever(observer)
        verify(observer).onChanged(listOf(dummyTvShowCast))
    }
}