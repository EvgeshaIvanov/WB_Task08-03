package com.example.favoritecats.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.favoritecats.MainViewModel
import com.example.favoritecats.MainViewModelFactory
import com.example.favoritecats.databinding.FragmentVoteBinding
import com.example.favoritecats.model.FavouriteCatData
import com.example.favoritecats.network.KtorInstance.Companion.DISLIKE
import com.example.favoritecats.network.KtorInstance.Companion.LIKE
import com.example.favoritecats.network.KtorInstance.Companion.SUB_ID
import com.example.favoritecats.network.RepositoryImpl
import com.facebook.drawee.backends.pipeline.Fresco


class VoteFragment : Fragment() {

    private lateinit var binding: FragmentVoteBinding

    private lateinit var viewModel: MainViewModel

    private var imageId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = RepositoryImpl
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCat()
        viewModel.list.observe(viewLifecycleOwner) { response ->
            response.forEach { cat ->
                imageId = cat.id
                val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(cat.url)
                    .build()
                binding.imageCat.controller = controller
            }
        }

        binding.likeButton.setOnClickListener {
            viewModel.getCat()
            viewModel.like(imageId, value = LIKE, subId = SUB_ID)
        }

        binding.dislikeButton.setOnClickListener {
            viewModel.getCat()
            viewModel.like(imageId, value = DISLIKE, subId = SUB_ID)
        }
    }

}