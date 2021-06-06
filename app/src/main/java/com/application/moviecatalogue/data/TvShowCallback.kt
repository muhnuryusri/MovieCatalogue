package com.application.moviecatalogue.data

import com.application.moviecatalogue.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(tvShow: TvShowEntity)
}