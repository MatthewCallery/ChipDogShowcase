package com.example.chipdogshowcase

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.example.chipdogshowcase.sharedpref.SharedPreferencesService

@RunWith(AndroidJUnit4::class)
class DogBreedsFragmentInstrumentedTests {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testCheckRecyclerViewAdapterNotNull() {
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.dog_breeds_fragment_list)
        assert(recyclerView.adapter != null)
    }

    @Test
    fun testRecyclerViewItemText() {
        val mContext = activityRule.activity.applicationContext
        val service = SharedPreferencesService(mContext)
        val list = "{\"message\":{\"affenpinscher\":[],\"african\":[],\"airedale\":[],\"akita\":[],\"appenzeller\":[],\"australian\":[\"shepherd\"]},\"status\":\"success\"}"
        service.saveDogBreedList(list)
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.dog_breeds_fragment_list)
        val textView = recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.findViewById<TextView>(R.id.dog_breeds_item_view_name)
        assert(textView?.text == "affenpinscher")
    }
}