package com.example.uxindividualassignment2

import android.app.Application

class App : Application() {
    companion object {
        var game = Game()
    }
    override fun onCreate() {
        super.onCreate()
    }
}