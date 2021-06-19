package com.application.moviecatalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.application.moviecatalogue.data.source.local.LocalDataSource
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.utils.AppExecutors
import com.application.moviecatalogue.utils.DataDummy
import com.application.moviecatalogue.utils.LiveDataTestUtil
import com.application.moviecatalogue.utils.PagedListUtil
import com.application.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

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
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListMovie()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovies()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovies()))
        verify(local).getListMovie()
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.getDummyDetailMovie()
        `when`(local.getMovieDetailData(movieId)).thenReturn(dummyMovie)

        val movieDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getDetailMovie(movieId))
        verify(local).getMovieDetailData(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getListTvShow()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTvShows()))
        verify(local).getListTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = DataDummy.getDummyDetailTvShow()
        `when`(local.getTvShowDetailData(tvShowId)).thenReturn(dummyTvShow)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowDetailData(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getMovieCast() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CastEntity>
        `when`(local.getCast()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovieCast(movieCastId)

        val movieCastEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovieCast()))
        verify(local).getCast()
        assertNotNull(movieCastEntities.data)
        assertEquals(movieCast.size.toLong(), movieCastEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowCast() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CastEntity>
        `when`(local.getCast()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShowCast(tvShowCastId)

        val tvShowCastEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTvShowCast()))
        verify(local).getCast()
        assertNotNull(tvShowCastEntities.data)
        assertEquals(tvShowCast.size.toLong(), tvShowCastEntities.data?.size?.toLong())
    }
}