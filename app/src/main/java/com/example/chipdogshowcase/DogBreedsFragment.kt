package com.example.chipdogshowcase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chipdogshowcase.databinding.FragmentDogBreedsBinding

class DogBreedsFragment : Fragment() {
    private lateinit var binding: FragmentDogBreedsBinding
    private lateinit var viewModel: DogBreedsViewModel
    private lateinit var viewModelFactory: DogBreedsViewModelFactory
    private lateinit var adapter: DogBreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_breeds, container, false)

        // ViewModel
        viewModelFactory = DogBreedsViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(DogBreedsViewModel::class.java)
        binding.dogBreedsViewModel = viewModel

        // RecyclerView and Adapter
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = DogBreedsAdapter()
        binding.dogBreedsFragmentList.adapter = adapter
        viewModel.breeds.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}