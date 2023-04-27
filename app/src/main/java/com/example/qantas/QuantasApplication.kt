package com.example.qantas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuantasApplication : Application() {

  override fun onCreate() {
    super.onCreate()
  }
}