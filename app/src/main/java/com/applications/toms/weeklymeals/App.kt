package com.applications.toms.weeklymeals

import android.app.Application
import com.applications.toms.weeklymeals.di.initDi

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

}