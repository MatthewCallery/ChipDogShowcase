package com.example.chipdogshowcase

fun String.capitaliseWords(): String =
    split(" ").map { it.capitalize() }.joinToString(" ")
