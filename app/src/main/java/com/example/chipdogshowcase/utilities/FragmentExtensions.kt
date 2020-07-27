package com.example.chipdogshowcase.utilities

import androidx.fragment.app.Fragment
import com.example.chipdogshowcase.MainActivity

fun Fragment.setMainActivityTitle(title: String) {
    (requireActivity() as MainActivity).title = title
}