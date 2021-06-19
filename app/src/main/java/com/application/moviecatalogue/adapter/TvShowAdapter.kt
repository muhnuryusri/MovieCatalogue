package com.application.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.moviecatalogue.BuildConfig
import com.application.moviecatalogue.data.TvShowCallback
import com.application.moviecatalogue.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter(private val callback: TvShowCallback) : PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val listItems = ArrayList<TvShowEntity>()

    fun setData(data: List<TvShowEntity>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + tvShow.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(290, 410))
                    .into(imgPoster)
                tvTitle.text = tvShow.title
                tvScore.text = tvShow.score.toString()

                item.setOnClickListener {
                    callback.onItemClicked(tvShow)
                }
            }
        }
    }
}