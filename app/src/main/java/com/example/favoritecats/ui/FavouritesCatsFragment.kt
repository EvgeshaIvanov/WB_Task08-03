package com.example.favoritecats.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritecats.MainViewModel
import com.example.favoritecats.MainViewModelFactory
import com.example.favoritecats.databinding.FragmentFavouritesCatsBinding
import com.example.favoritecats.network.RepositoryImpl


class FavouritesCatsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var catsAdapter: CatsAdapter
    private var imagesId = mutableListOf<String>()
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
        val newList = mutableListOf<String>()
        val urlList = mutableListOf<String>()

        viewModel.favouriteCatsList.observe(viewLifecycleOwner) {
            catsAdapter.cats = it.filter { it.value == 0 }
            catsAdapter.clickOnCatListener = { cat ->
                viewModel.deleteFromFavouriteList(cat.id)
            }
            viewModel.favouriteCats()
            //it.filter { it.value == 0 }.forEach { newList.add(it.image_id) }
            //it.filter { it.value == 0 }.forEach { imageId.add(it.image_id ) }
            //it.filter { it.value ==0 }.forEach { imageId.add(viewModel.setImageFromId(it.image_id).toString()) }
            //catsAdapter.cats = imageId.forEach{ viewModel.setImageFromId(it)}
            it.filter { it.value == 0 }.forEach { imagesId.add(it.image_id) }
            Log.i("LISTTT", imagesId.toString())
            // catsAdapter.cats = newList.forEach { viewModel.setImageFromId(it) }

        }
        viewModel.imagesCats.observe(viewLifecycleOwner) {
           // for (el in imagesId){
            //    viewModel.setImageFromId(el)
            //    Log.i("CIRCLE", el)

            //}
            //= newList.forEach { urlList.add(viewModel.setImageFromId(it).toString()) }
            //catsAdapter.cats = listOf(it)
            Log.i("LISTTT", urlList.toString())
        }

    }
    fun clickOnCatListener(){

    }

    private fun initRv() {
        catsAdapter = CatsAdapter()
        binding.catsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catsAdapter
        }
        clickOnCatListener()
    }
}