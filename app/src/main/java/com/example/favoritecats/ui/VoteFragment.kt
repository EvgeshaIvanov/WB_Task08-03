package com.example.favoritecats.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.favoritecats.data.network.KtorInstance.Companion.LIKE
import com.example.favoritecats.data.network.KtorInstance.Companion.SUB_ID
import com.example.favoritecats.ui.viewmodel.MainViewModel
import com.example.favoritecats.ui.viewmodel.MainViewModelFactory
import com.example.favoritecats.databinding.FragmentVoteBinding
import com.example.favoritecats.data.network.NetworkRepository
import com.example.favoritecats.data.room.AppDatabase
import com.example.favoritecats.data.room.RoomCatsRepository
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
        val networkRepository = NetworkRepository
        val dao = AppDatabase.getDatabase(requireActivity().applicationContext).catsDao()
        val roomRepository = RoomCatsRepository(dao)
        val viewModelFactory = MainViewModelFactory(networkRepository, roomRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.randomCat()
        viewModel.randomCats.observe(viewLifecycleOwner) { response ->
            response.forEach { cat ->
                imageId = cat.id
                val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(cat.url)
                    .build()
                binding.imageCat.controller = controller
            }
        }

        binding.likeButton.setOnClickListener {
            viewModel.randomCat()
            viewModel.likeCat(imageId, value = LIKE, subId = SUB_ID)

        }

        binding.dislikeButton.setOnClickListener {
            viewModel.randomCat()
        }
    }

}