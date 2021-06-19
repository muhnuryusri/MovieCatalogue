package com.application.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.R
import com.application.moviecatalogue.adapter.MovieAdapter
import com.application.moviecatalogue.data.MovieCallback
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.databinding.FragmentFavoriteBinding
import com.application.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.ui.favorite.FavoriteViewModel
import com.application.moviecatalogue.utils.DataHelper
import com.application.moviecatalogue.viewmodel.MovieViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory
import com.application.moviecatalogue.vo.Status

class FavoriteMovieFragment : Fragment(), MovieCallback {
    private lateinit var binding: FragmentFavoriteMovieBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { listMovie ->
            binding.rvMovie.adapter?.let { adapter ->
                when (adapter) {
                    is MovieAdapter -> {
                        adapter.submitList(listMovie)
                        adapter.setData(listMovie)
                    }
                }
            }
            showLoading(false)
        })
        showLoading(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter = MovieAdapter(this)

        binding.rvMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMovie.adapter = adapter
    }

    override fun onItemClicked(movie: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, DataHelper.MOVIE)
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