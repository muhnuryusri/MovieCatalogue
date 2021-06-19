package com.application.moviecatalogue.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.adapter.MovieAdapter
import com.application.moviecatalogue.data.MovieCallback
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.utils.DataHelper.MOVIE
import com.application.moviecatalogue.databinding.FragmentMovieBinding
import com.application.moviecatalogue.viewmodel.MovieViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory
import com.application.moviecatalogue.vo.Status

class MovieFragment : Fragment(), MovieCallback {
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        movieViewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        binding.rvMovie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    listMovie.data?.let { adapter.setData(it) }
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        showLoading(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter = MovieAdapter(this@MovieFragment)

        binding.rvMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMovie.adapter = adapter
    }

    override fun onItemClicked(movie: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
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