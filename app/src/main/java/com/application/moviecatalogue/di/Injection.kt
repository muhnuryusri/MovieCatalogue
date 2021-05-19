package com.application.moviecatalogue.di

import android.content.Context
import com.application.moviecatalogue.data.source.remote.CatalogueRepository
import com.application.moviecatalogue.data.source.remote.response.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}