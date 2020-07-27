package com.example.chipdogshowcase

import android.app.Application
import android.content.Context

class FetchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}