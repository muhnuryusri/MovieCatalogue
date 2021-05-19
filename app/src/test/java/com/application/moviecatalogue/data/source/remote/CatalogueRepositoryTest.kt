package com.application.moviecatalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.application.moviecatalogue.data.source.remote.response.RemoteDataSource
import com.application.moviecatalogue.utils.DataDummy
import com.application.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val moviesResponse = DataDummy.getMoviesResponse()
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.getMovieDetailResponse()

    private val tvShowResponse = DataDummy.getTvShowResponse()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.getTvShowDetailResponse()

    private val movieCastResponse = DataDummy.getMovieCastResponse()
    private val movieCastId = movieCastResponse.id
    private val movieCast = movieCastResponse.cast

    private val tvShowCastResponse = DataDummy.getTvShowCastResponse()
    private val tvShowCastId = tvShowCastResponse.id
    private val tvShowCast = tvShowCastResponse.cast

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieLoaded(movieDetail)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))

        val movieDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(tvShowDetail)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))

        val tvShowDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
    }

    @Test
    fun getMovieCast() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCastCallback).onCastLoaded(movieCast)
            null
        }.`when`(remote).getMovieCast(any(), eq(movieCastId))

        val movieCastEntity = LiveDataTestUtil.getValue(catalogueRepository.getMovieCast(movieCastId))
        verify(remote).getMovieCast(any(), eq(movieCastId))
        assertNotNull(movieCastEntity)
        assertEquals(movieCast.size, movieCastEntity.size)
    }

    @Test
    fun getTvShowCast() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCastCallback).onCastLoaded(tvShowCast)
            null
        }.`when`(remote).getTvShowCast(any(), eq(tvShowCastId))

        val tvShowCastEntity = LiveDataTestUtil.getValue(catalogueRepository.getTvShowCast(tvShowCastId))
        verify(remote).getTvShowCast(any(), eq(tvShowCastId))
        assertNotNull(tvShowCastEntity)
        assertEquals(tvShowCast.size, tvShowCastEntity.size)
    }
}