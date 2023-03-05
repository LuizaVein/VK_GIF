package com.example.vk_gif.view.search.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.target.ImageViewTarget
import com.example.vk_gif.R
import com.example.vk_gif.databinding.GiphyItemBinding
import com.example.vk_gif.model.GiphyModel

class GiphyModelViewHolder(giphyItemLayoutBinding: GiphyItemBinding)
        : RecyclerView.ViewHolder(giphyItemLayoutBinding.root){
    private val binding = giphyItemLayoutBinding

    fun bind(giphyItem: GiphyModel?, handler: (GiphyModel) -> Unit) {
        if (giphyItem != null) {
            binding.image.setOnClickListener {
                handler.invoke(giphyItem)
            }
            Glide.with(binding.image.context)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(giphyItem.images.original.url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.image)
        }
    }

    }
