package com.example.chipdogshowcase.dogimages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chipdogshowcase.*
import com.example.chipdogshowcase.databinding.FragmentDogImagesBinding
import com.example.chipdogshowcase.models.DogBreed
import com.example.chipdogshowcase.utilities.setMainActivityTitle

class DogImagesFragment : Fragment() {
    private lateinit var binding: FragmentDogImagesBinding
    private lateinit var viewModel: DogImagesViewModel
    private lateinit var viewModelFactory: DogImagesViewModelFactory
    private lateinit var dogBreed: DogBreed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_images, container, false)

        //TODO handle error
        dogBreed = DogImagesFragmentArgs.fromBundle(requireArguments()).selectedProperty

        setMainActivityTitle(dogBreed.breedName)
        setViewModel()
        setRecyclerViewAndAdapter()
        return binding.root
    }

    private fun setViewModel() {
        viewModelFactory = DogImagesViewModelFactory(dogBreed, DI.repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DogImagesViewModel::class.java)
        binding.dogImagesViewModel = viewModel
    }

    private fun setRecyclerViewAndAdapter() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dogImagesFragmentList.adapter = DogImagesAdapter()
    }
}