package com.application.moviecatalogue.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.adapter.CastAdapter
import com.application.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.application.moviecatalogue.utils.DataHelper.MOVIE
import com.application.moviecatalogue.utils.DataHelper.TV_SHOW
import com.application.moviecatalogue.databinding.ActivityDetailBinding
import com.application.moviecatalogue.viewmodel.CastViewModel
import com.application.moviecatalogue.viewmodel.DetailViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_CATEGORY = "extra_CATEGORY"
    }

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var castViewModel: CastViewModel
    private lateinit var castAdapter: CastAdapter
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        castViewModel = ViewModelProvider(this, factory)[CastViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getInt(EXTRA_DATA, 0)
            val categoryId = extras.getString(EXTRA_CATEGORY)
            if (categoryId == MOVIE) {
                detailViewModel.getSelectedMovie(dataId).observe(this, { detail ->
                    populateDetailMovieData(detail)
                })

                castViewModel.getListMovieCast(dataId).observe(this, { listMovieCast ->
                    binding.rvCast.adapter?.let { adapter ->
                        when (adapter) {
                            is CastAdapter -> adapter.setData(listMovieCast)
                        }
                    }
                })
            }   else if (categoryId == TV_SHOW) {
                detailViewModel.getSelectedTvShow(dataId).observe(this, { detail ->
                    populateDetailTvShowData(detail)
                })

                castViewModel.getListTvShowCast(dataId).observe(this, { listTvShowCast ->
                    binding.rvCast.adapter?.let { adapter ->
                        when (adapter) {
                            is CastAdapter -> adapter.setData(listTvShowCast)
                        }
                    }
                })
            }
        }

        showRecyclerList()
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailMovieData(movieData: MovieDetailEntity) {
        binding.tvTitle.text = movieData.title
        binding.tvRelease.text = movieData.release
        binding.tvDuration.text = movieData.duration.toString() + " minutes"
        binding.tvScore.text = movieData.score.toString()
        binding.tvGenre.text = movieData.genre.toString()
        binding.tvOverview.text = movieData.overview

        Glide.with(this)
            .load(IMAGE_URL + movieData.preview)
            .centerCrop()
            .apply(RequestOptions().override(510, 300))
            .into(binding.imgPreview)

        Glide.with(this)
            .load(IMAGE_URL + movieData.poster)
            .centerCrop()
            .apply(RequestOptions().override(330, 500))
            .into(binding.imgPoster)
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailTvShowData(tvShowData: TvShowDetailEntity) {
        binding.tvTitle.text = tvShowData.title
        binding.tvRelease.text = tvShowData.release
        binding.tvDuration.text = tvShowData.season.toString() + " Seasons"
        binding.tvScore.text = tvShowData.score.toString()
        binding.tvGenre.text = tvShowData.genre.toString()
        binding.tvOverview.text = tvShowData.overview

        Glide.with(this)
                .load(IMAGE_URL + tvShowData.preview)
                .centerCrop()
                .apply(RequestOptions().override(510, 300))
                .into(binding.imgPreview)

        Glide.with(this)
                .load(IMAGE_URL + tvShowData.poster)
                .centerCrop()
                .apply(RequestOptions().override(330, 500))
                .into(binding.imgPoster)
    }

    private fun showRecyclerList() {
        castAdapter = CastAdapter()

        binding.rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCast.adapter = castAdapter
    }
}