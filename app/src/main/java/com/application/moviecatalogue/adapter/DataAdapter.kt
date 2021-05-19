package com.application.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.moviecatalogue.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.data.DataCallback
import com.application.moviecatalogue.data.source.local.entity.DataEntity
import com.application.moviecatalogue.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DataAdapter(private val callback: DataCallback) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private val listItems = ArrayList<DataEntity>()

    fun setData(data: List<DataEntity>) {
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
        fun bind(data: DataEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(IMAGE_URL + data.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(290, 410))
                    .into(imgPoster)
                tvTitle.text = data.title
                tvScore.text = data.score.toString()

                item.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }
        }
    }
}