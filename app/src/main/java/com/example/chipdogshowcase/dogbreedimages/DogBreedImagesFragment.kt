package com.example.chipdogshowcase.dogbreedimages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chipdogshowcase.*
import com.example.chipdogshowcase.databinding.FragmentDogBreedImagesBinding

class DogBreedImagesFragment : Fragment() {
    private lateinit var binding: FragmentDogBreedImagesBinding
    private lateinit var viewModel: DogBreedImagesViewModel
    private lateinit var viewModelFactory: DogBreedImagesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_dog_breed_images, container, false)

        //TODO handle error
        val dogBreed = DogBreedImagesFragmentArgs.fromBundle(
            requireArguments()
        ).selectedProperty

        // ViewModel
        viewModelFactory =
            DogBreedImagesViewModelFactory(
                dogBreed, DI.repository
            )
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DogBreedImagesViewModel::class.java)
        binding.dogBreedImagesViewModel = viewModel

        // Set app bar title
        (requireActivity() as MainActivity).title = dogBreed.breedName

        // RecyclerView and Adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dogBreedImagesFragmentList.adapter =
            DogBreedImagesAdapter()

        return binding.root
    }
}