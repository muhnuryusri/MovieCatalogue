package com.application.moviecatalogue.data

import com.application.moviecatalogue.data.source.local.entity.DataEntity

interface DataCallback {
    fun onItemClicked(data: DataEntity)
}