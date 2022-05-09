package com.app.peyza.base

import android.app.Application
import com.c2c.data.local.PrefsManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass : Application() {
    
    override fun onCreate() {
        super.onCreate()
        PrefsManager.initialize(this)
    }

}

