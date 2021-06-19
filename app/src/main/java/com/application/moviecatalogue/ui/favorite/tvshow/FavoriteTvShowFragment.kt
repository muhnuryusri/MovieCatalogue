package com.application.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.R
import com.application.moviecatalogue.adapter.MovieAdapter
import com.application.moviecatalogue.adapter.TvShowAdapter
import com.application.moviecatalogue.data.MovieCallback
import com.application.moviecatalogue.data.TvShowCallback
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.application.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.ui.favorite.FavoriteViewModel
import com.application.moviecatalogue.utils.DataHelper
import com.application.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment(), TvShowCallback {
    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        favoriteViewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { listTvShow ->
            binding.rvTvshow.adapter?.let { adapter ->
                when (adapter) {
                    is TvShowAdapter -> {
                        adapter.submitList(listTvShow)
                        adapter.setData(listTvShow)
                    }
                }
            }
            showLoading(false)
        })
        showLoading(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter = TvShowAdapter(this)

        binding.rvTvshow.layoutManager = LinearLayoutManager(activity)
        binding.rvTvshow.adapter = adapter
    }

    override fun onItemClicked(tvShow: TvShowEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, DataHelper.TV_SHOW)
        context?.startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.shimmerView.startShimmer()
            binding.shimmerView.visibility = View.VISIBLE
        } else {
            binding.shimmerView.stopShimmer()
            binding.shimmerView.visibility = View.GONE
        }
    }
}