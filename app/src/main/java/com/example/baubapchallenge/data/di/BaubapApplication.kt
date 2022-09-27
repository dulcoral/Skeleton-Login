package com.example.baubapchallenge.data.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaubapApplication : Application() {

    companion object {
        private lateinit var instance: BaubapApplication

        fun getInstance(): BaubapApplication {
            return instance
        }

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}