package com.example.chipdogshowcase

import com.example.chipdogshowcase.models.DogBreed
import com.example.chipdogshowcase.models.addImageUrlList
import org.junit.Test
import org.junit.Assert.*

class DogBreedUnitTest {
    @Test
    fun testCreateDogBreed() {
        // given
        val breedName = "Jack Russell"

        // when
        val dogBreed = DogBreed(breedName)

        // then
        assertEquals(dogBreed.breedName, "Jack Russell")
    }

    @Test
    fun testAddImageUrls() {
        // given
        val imageUrls = arrayListOf<String>(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
        )
        val dogBreed = DogBreed("Spaniel")

        // when
        dogBreed.addImageUrlList(imageUrls)

        // then
        assertEquals(dogBreed.imageUrls[0], "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg")
        assertEquals(dogBreed.imageUrls[1], "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg")
    }
}