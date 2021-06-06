package com.application.moviecatalogue.data

import com.application.moviecatalogue.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(movie: MovieEntity)
}