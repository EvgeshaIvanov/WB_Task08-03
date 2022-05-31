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
import com.example.favoritecats.network.RepositoryImpl
import com.facebook.drawee.backends.pipeline.Fresco


class VoteFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var imageId = ""
    private var value = 0
    private lateinit var binding: FragmentVoteBinding

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
          response.forEach {
              imageId = it.id
              binding.catText.text = it.id
              Log.i("URIBLY", it.url)
                val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(it.url)
                    .build()
              binding.imageCat.controller = controller
          }
        }

        binding.likeButton.setOnClickListener {
            viewModel.getCat()
            viewModel.like(imageId, value = 0, subId = "levi-2708")
            Log.i("IMAGEID", imageId)
        }

        binding.dislikeButton.setOnClickListener {
            viewModel.getCat()
            viewModel.like(imageId, value = 1, subId = "levi-2708")
            //value = 0
        }
    }

}