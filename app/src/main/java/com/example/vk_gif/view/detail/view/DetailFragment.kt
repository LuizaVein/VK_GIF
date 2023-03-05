package com.example.vk_gif.view.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vk_gif.R
import com.example.vk_gif.databinding.DetailFragmentBinding

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            view.findNavController().popBackStack()
        }
        initText()
    }

    private fun initText() {
        binding.titleText.text = buildString {
            append(getString(R.string.gyphy_title))
            append(args.model.title)
        }
        binding.usernameText.text = buildString {
            append(getString(R.string.username_title))
            if (args.model.username.isEmpty()) {
                append(getString(R.string.unknown))
            } else {
                append(args.model.username)
            }
        }
        binding.ratingText.text = buildString {
            append(getString(R.string.rating_title))
            append(args.model.rating)
        }

        binding.descriptionText.text = buildString {
            if (args.model.altText != null) {
                append(getString(R.string.description_title))
                append(args.model.altText)
            } else {
                append(getString(R.string.description_title))
                append(getString(R.string.unknown))
            }
        }

        binding.datatimeText.text = buildString {
            if (args.model.createDatetime != null) {
                append(getString(R.string.create_datetime_title))
                append(args.model.createDatetime)
            } else {
                append(getString(R.string.create_datetime_title))
                append(getString(R.string.unknown))
            }
        }


        Glide.with(requireContext())
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(args.model.images.original.url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .into(binding.detailGiphy)
    }
}