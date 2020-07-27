package com.example.chipdogshowcase

import com.example.chipdogshowcase.models.DogBreed
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
}