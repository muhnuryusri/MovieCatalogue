package com.application.moviecatalogue.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.adapter.TvShowAdapter
import com.application.moviecatalogue.data.TvShowCallback
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.utils.DataHelper
import com.application.moviecatalogue.databinding.FragmentTvShowBinding
import com.application.moviecatalogue.viewmodel.TvShowViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory
import com.application.moviecatalogue.vo.Status

class TvShowFragment : Fragment(), TvShowCallback {
    private lateinit var adapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        tvShowViewModel.getListTvShow().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        binding.rvTvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    listTvShow.data?.let { adapter.setData(it) }
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
        adapter = TvShowAdapter(this@TvShowFragment)

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