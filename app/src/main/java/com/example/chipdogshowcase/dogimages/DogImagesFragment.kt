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

class DogImagesFragment : Fragment() {
    private lateinit var binding: FragmentDogImagesBinding
    private lateinit var viewModel: DogImagesViewModel
    private lateinit var viewModelFactory: DogImagesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_dog_images, container, false)

        //TODO handle error
        val dogBreed = DogImagesFragmentArgs.fromBundle(
            requireArguments()
        ).selectedProperty

        // ViewModel
        viewModelFactory =
            DogImagesViewModelFactory(
                dogBreed, DI.repository
            )
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DogImagesViewModel::class.java)
        binding.dogImagesViewModel = viewModel

        // Set app bar title
        (requireActivity() as MainActivity).title = dogBreed.breedName

        // RecyclerView and Adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dogImagesFragmentList.adapter =
            DogImagesAdapter()

        return binding.root
    }
}