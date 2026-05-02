package com.example.tutorw7

import android.app.Application

class App : Application() {
    companion object {
        lateinit var api: ApiService
    }
    override fun onCreate() {
        super.onCreate()
        api = RetrofitClient.instance
    }
}