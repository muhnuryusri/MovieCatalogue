package com.application.moviecatalogue.ui.fragment.tvshow

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
import com.application.moviecatalogue.utils.DataHelper
import com.application.moviecatalogue.databinding.FragmentTvShowBinding
import com.application.moviecatalogue.viewmodel.TvShowViewModel
import com.application.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(), DataCallback {
    private lateinit var adapter: DataAdapter
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
            binding.rvTvshow.adapter?.let { adapter ->
                when (adapter) {
                    is DataAdapter -> adapter.setData(listTvShow)
                }
            }
            showLoading(false)
        })
        showLoading(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        adapter = DataAdapter(this@TvShowFragment)

        binding.rvTvshow.layoutManager = LinearLayoutManager(activity)
        binding.rvTvshow.adapter = adapter
    }

    override fun onItemClicked(data: DataEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
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