package com.example.catpicturesnew

import android.app.Application
import com.example.catpicturesnew.data.AppContainer
import com.example.catpicturesnew.data.DefaultAppContainer


class PicumApplication: Application() {
  lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}