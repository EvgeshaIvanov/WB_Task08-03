package com.example.favoritecats.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritecats.MainViewModel
import com.example.favoritecats.MainViewModelFactory
import com.example.favoritecats.R
import com.example.favoritecats.databinding.FragmentFavouritesCatsBinding
import com.example.favoritecats.network.RepositoryImpl


class FavouritesCatsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var catsAdapter: CatsAdapter

    private lateinit var binding: FragmentFavouritesCatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesCatsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        val repository = RepositoryImpl
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.favouriteCats()
        viewModel.favouriteCatsList.observe(viewLifecycleOwner){
                catsAdapter.cats = it
        }

    }
    private fun initRv(){
        catsAdapter = CatsAdapter()
        binding.catsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catsAdapter
        }
    }
}