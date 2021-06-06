package com.application.moviecatalogue.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.adapter.CastAdapter
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.utils.DataHelper.MOVIE
import com.application.moviecatalogue.utils.DataHelper.TV_SHOW
import com.application.moviecatalogue.databinding.ActivityDetailBinding
import com.application.moviecatalogue.viewmodel.CastViewModel
import com.application.moviecatalogue.viewmodel.DetailViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory
import com.application.moviecatalogue.vo.Status
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
                    when (detail.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> {
                            if (detail.data != null) {
                                populateDetailMovieData(detail.data)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

                castViewModel.getListMovieCast(dataId).observe(this, { listMovieCast ->
                    if (listMovieCast != null) {
                        when (listMovieCast.status) {
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                binding.rvCast.adapter?.let { adapter ->
                                    when (adapter) {
                                        is CastAdapter -> {
                                            listMovieCast.data?.let { adapter.setData(it) }
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            }   else if (categoryId == TV_SHOW) {
                detailViewModel.getSelectedTvShow(dataId).observe(this, { detail ->
                    when (detail.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> {
                            if (detail.data != null) {
                                populateDetailTvShowData(detail.data)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

                castViewModel.getListTvShowCast(dataId).observe(this, { listTvShowCast ->
                    if (listTvShowCast != null) {
                        when (listTvShowCast.status) {
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                binding.rvCast.adapter?.let { adapter ->
                                    when (adapter) {
                                        is CastAdapter -> {
                                            listTvShowCast.data?.let { adapter.setData(it) }
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        showRecyclerList()
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailMovieData(movieData: MovieEntity) {
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
    private fun populateDetailTvShowData(tvShowData: TvShowEntity) {
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