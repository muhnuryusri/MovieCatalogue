package com.application.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.moviecatalogue.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.data.MovieCallback
import com.application.moviecatalogue.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val callback: MovieCallback) : PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val listItems = ArrayList<MovieEntity>()

    fun setData(data: List<MovieEntity>) {
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
        fun bind(movie: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(IMAGE_URL + movie.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(290, 410))
                    .into(imgPoster)
                tvTitle.text = movie.title
                tvScore.text = movie.score.toString()

                item.setOnClickListener {
                    callback.onItemClicked(movie)
                }
            }
        }
    }
}