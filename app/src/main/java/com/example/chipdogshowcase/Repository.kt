package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import com.example.chipdogshowcase.network.DogApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val sharedPrefService: SavedPreferencesService, private val dogApiService: DogApiService) {
    val dogBreeds : LiveData<List<DogBreed>> = sharedPrefService

    suspend fun refreshDogBreeds() {
        withContext(Dispatchers.IO) {
            val dogBreeds = dogApiService.getBreedsAsync()
            sharedPrefService.saveDogBreedList(dogBreeds)
        }
    }

    suspend fun getDogImages(dogBreed: DogBreed) : ArrayList<String> {
        val cleanedBreedName = cleanBreedName(dogBreed.breedName)
        val dogImages = dogApiService.getDogImagesAsync(cleanedBreedName)
        return dogImages.asDogImageUrlList()
    }

    private fun cleanBreedName(breedName: String) : String {
        val nameArray = breedName.split(" ")
        var cleanedBreedName = ""
        if (nameArray.size == 1) {
            cleanedBreedName = nameArray[0].toLowerCase()
        } else if (nameArray.size == 2) {
            cleanedBreedName = "${nameArray[1].toLowerCase()}/${nameArray[0].toLowerCase()}"
        }
        return cleanedBreedName
    }
}