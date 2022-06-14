package com.example.favoritecats

import android.app.Application
import androidx.room.Room
import com.example.favoritecats.data.room.AppDatabase
import com.facebook.drawee.backends.pipeline.Fresco

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}