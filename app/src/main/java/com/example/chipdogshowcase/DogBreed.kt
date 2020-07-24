package com.example.chipdogshowcase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogBreed(val breedName: String = "") : Parcelable