package com.example.chipdogshowcase.dogbreeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chipdogshowcase.*
import com.example.chipdogshowcase.databinding.FragmentDogBreedsBinding
import com.example.chipdogshowcase.utilities.setMainActivityTitle

class DogBreedsFragment : Fragment() {
    private lateinit var binding: FragmentDogBreedsBinding
    private lateinit var viewModel: DogBreedsViewModel
    private lateinit var viewModelFactory: DogBreedsViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_breeds, container, false)
        setMainActivityTitle(getString(R.string.titleDogBreedsFragment))
        setViewModel()
        setRecyclerViewAndAdapter()
        setNavigationObserver()
        return binding.root
    }

    private fun setViewModel() {
        viewModelFactory = DogBreedsViewModelFactory(DI.repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DogBreedsViewModel::class.java)
        binding.dogBreedsViewModel = viewModel
    }

    private fun setRecyclerViewAndAdapter() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dogBreedsFragmentList.adapter =
            DogBreedsAdapter(
                DogBreedsAdapter.OnClickListener {
                    viewModel.displayDogBreedImages(it)
                }
            )
    }

    private fun setNavigationObserver() {
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    DogBreedsFragmentDirections.actionDogBreedsFragmentToDogImagesFragment(it)
                )
                viewModel.displayDogBreedImagesComplete()
            }
        })
    }
}