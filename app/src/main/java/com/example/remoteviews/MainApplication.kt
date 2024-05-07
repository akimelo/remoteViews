package com.example.remoteviews

import android.app.Application
import io.karte.android.KarteApp
import io.karte.android.variables.Variables

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KarteApp.setup(this)
        Variables.fetch()
    }
}