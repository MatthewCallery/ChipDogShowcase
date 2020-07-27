package com.example.chipdogshowcase

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.chipdogshowcase.models.DogBreed
import com.example.chipdogshowcase.models.asDogBreedList

class SavedPreferencesService(private val context: Context) : LiveData<List<DogBreed>>() {
    private lateinit var sharedPreferences: SharedPreferences

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        value = getValueFromPreferences()
    }

    override fun onActive() {
        super.onActive()
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.SHARED_PREF_KEY), Context.MODE_PRIVATE)
        value = getValueFromPreferences()
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }

    private fun getValueFromPreferences() : List<DogBreed> {
        val dogBreedJson = sharedPreferences.getString(context.getString(R.string.SHARED_PREF_DOG_BREED_LIST), "") as String
        return dogBreedJson.asDogBreedList()
    }

    fun saveDogBreedList(list: String) {
        val sharedPreferences = context.getSharedPreferences(context.getString(R.string.SHARED_PREF_KEY), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.SHARED_PREF_DOG_BREED_LIST), list)
        editor.apply()
    }
}