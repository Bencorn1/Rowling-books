package com.example.rowlingapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RowlingApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}