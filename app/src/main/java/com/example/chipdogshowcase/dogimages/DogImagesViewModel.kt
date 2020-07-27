package com.example.chipdogshowcase.dogimages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chipdogshowcase.network.DogApiStatus
import com.example.chipdogshowcase.models.DogBreed
import com.example.chipdogshowcase.Repository
import com.example.chipdogshowcase.models.addImageUrlList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogImagesViewModel(private var breed: DogBreed, private var repository: Repository) : ViewModel() {
    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>>
        get() = _images

    private val _status = MutableLiveData<DogApiStatus>()
    val status: LiveData<DogApiStatus>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getDogImages()
    }

    private fun getDogImages() {
        coroutineScope.launch {
            try {
                _status.value = DogApiStatus.LOADING
                breed.addImageUrlList(repository.getDogImages(breed))
                _status.value = DogApiStatus.DONE
                _images.value = breed.imageUrls
            } catch (e: Exception) {
                _status.value = DogApiStatus.ERROR
                _images.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}