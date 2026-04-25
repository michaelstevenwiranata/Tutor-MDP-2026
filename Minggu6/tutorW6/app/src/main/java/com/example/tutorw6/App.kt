package com.example.tutorw6

import android.app.Application

class App : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
    }
}