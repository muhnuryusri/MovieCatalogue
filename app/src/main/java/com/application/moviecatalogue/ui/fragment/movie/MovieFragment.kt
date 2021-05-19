package com.application.moviecatalogue.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.adapter.DataAdapter
import com.application.moviecatalogue.data.DataCallback
import com.application.moviecatalogue.data.source.local.entity.DataEntity
import com.application.moviecatalogue.utils.DataHelper.MOVIE
import com.application.moviecatalogue.databinding.FragmentMovieBinding
import com.application.moviecatalogue.viewmodel.MovieViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), DataCallback {
    private lateinit var adapter: DataAdapter
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
            binding.rvMovie.adapter?.let { adapter ->
                when (adapter) {
                    is DataAdapter -> adapter.setData(listMovie)
                }
            }
            showLoading(false)
        })
        showLoading(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter = DataAdapter(this@MovieFragment)

        binding.rvMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMovie.adapter = adapter
    }

    override fun onItemClicked(data: DataEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
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