package com.example.weeklymeals

import android.app.Application
import com.example.weeklymeals.di.initDi

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

}