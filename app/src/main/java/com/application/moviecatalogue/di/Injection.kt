package com.application.moviecatalogue.di

import android.content.Context
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.data.source.local.LocalDataSource
import com.application.moviecatalogue.data.source.local.room.CatalogueDatabase
import com.application.moviecatalogue.data.source.remote.RemoteDataSource
import com.application.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}