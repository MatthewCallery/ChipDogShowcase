package com.example.chipdogshowcase

import androidx.fragment.app.Fragment

fun Fragment.setMainActivityTitle(title: String) {
    (requireActivity() as MainActivity).title = title
}