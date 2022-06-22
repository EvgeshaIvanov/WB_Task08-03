package com.example.favoritecats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.favoritecats.data.network.NetworkRepository
import com.example.favoritecats.data.room.AppDatabase
import com.example.favoritecats.data.room.RoomCatsRepository
import com.example.favoritecats.databinding.FragmentFavouritesCatsBinding
import com.example.favoritecats.ui.utils.CatsAdapter
import com.example.favoritecats.ui.viewmodel.MainViewModel
import com.example.favoritecats.ui.viewmodel.MainViewModelFactory


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
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        val networkRepository = NetworkRepository
        val dao = AppDatabase.getDatabase(requireActivity().applicationContext).catsDao()
        val roomRepository = RoomCatsRepository(dao)
        val viewModelFactory = MainViewModelFactory(networkRepository, roomRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.refreshListOfFavoriteCats()
        viewModel.favouriteCatsList.observe(viewLifecycleOwner) { listOfCats ->
            catsAdapter.cats = listOfCats
            catsAdapter.clickOnCatListener = { cat ->
                viewModel.deleteFromFavouriteList(cat.id, cat.uid)
            }
        }
    }

    private fun initRecyclerView() {
        catsAdapter = CatsAdapter()
        binding.catsRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = catsAdapter
        }
    }
}