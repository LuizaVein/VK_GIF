package com.example.vk_gif.view.search.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.vk_gif.databinding.GiphyItemBinding
import com.example.vk_gif.model.GiphyModel

class GiphyModelAdapter(private val context: Context,
 private val handler: (GiphyModel) -> Unit): PagingDataAdapter<GiphyModel, GiphyModelViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyModelViewHolder {
        val binding = GiphyItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return GiphyModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiphyModelViewHolder, position: Int) {
        holder.bind(getItem(position), handler)
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<GiphyModel>() {
        override fun areItemsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem == newItem
        }
    }
}