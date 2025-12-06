package com.example.quizzy

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.example.quizzy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizzyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Log all FirebaseApp instances
        val firebaseApps = FirebaseApp.getApps(this)
        Log.d("FirebaseDebug", "FirebaseApp instances: $firebaseApps")

        // Optional: check default app specifically
        firebaseApps.find { it.name == FirebaseApp.DEFAULT_APP_NAME }?.let {
            Log.d("FirebaseDebug", "Default FirebaseApp initialized successfully: ${it.options}")
        }

        // Initialize Koin
        startKoin {
            androidContext(this@QuizzyApplication)
            modules(appModule)
        }
    }
}
