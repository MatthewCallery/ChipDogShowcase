package com.example.chipdogshowcase

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.chipdogshowcase.sharedpref.SharedPreferencesService
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesInstrumentedTests {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSaveDogBreedList() {
        val list = "{\"message\":{\"affenpinscher\":[],\"african\":[],\"airedale\":[],\"akita\":[],\"appenzeller\":[],\"australian\":[\"shepherd\"]},\"status\":\"success\"}"
        val mContext = activityRule.activity.applicationContext
        val service = SharedPreferencesService(mContext)
        service.saveDogBreedList(list)

        val sharedPreferences = mContext.getSharedPreferences(mContext.getString(R.string.SHARED_PREF_KEY), Context.MODE_PRIVATE)
        val newList = sharedPreferences.getString(mContext.getString(R.string.SHARED_PREF_DOG_BREED_LIST), "") as String
        assert(newList == list)
    }
}