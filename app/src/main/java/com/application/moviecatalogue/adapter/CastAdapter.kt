package com.application.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.moviecatalogue.BuildConfig
import com.application.moviecatalogue.data.source.local.entity.CastEntity
import com.application.moviecatalogue.databinding.ItemCastBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CastAdapter : PagedListAdapter<CastEntity, CastAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastEntity>() {
            override fun areItemsTheSame(oldItem: CastEntity, newItem: CastEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: CastEntity, newItem: CastEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val listCasts = ArrayList<CastEntity>()

    fun setData(data: List<CastEntity>) {
        listCasts.clear()
        listCasts.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listCasts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCasts[position])
    }

    inner class ViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + data.photo)
                    .centerCrop()
                    .apply(RequestOptions().override(200, 200))
                    .into(imgPhoto)
                tvName.text = data.name
                tvCharacter.text = data.character
            }
        }
    }
}