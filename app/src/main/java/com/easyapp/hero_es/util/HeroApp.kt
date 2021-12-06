package com.easyapp.hero_es.util

import android.app.Application
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HeroApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //offline
        Firebase.database.setPersistenceEnabled(true)

    }
}