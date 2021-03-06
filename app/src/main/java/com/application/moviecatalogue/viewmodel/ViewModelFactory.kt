package com.application.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.moviecatalogue.data.CatalogueRepository
import com.application.moviecatalogue.di.Injection
import com.application.moviecatalogue.ui.favorite.FavoriteViewModel

class ViewModelFactory private constructor(private val catalogueRepository: CatalogueRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(CastViewModel::class.java) -> {
                CastViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(catalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class :" + modelClass.name)
        }
    }
}