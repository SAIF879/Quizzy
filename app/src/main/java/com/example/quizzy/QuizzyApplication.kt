package com.example.quizzy

import android.app.Application
import com.google.firebase.FirebaseApp
import com.example.quizzy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizzyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Initialize Koin
        startKoin {
            androidContext(this@QuizzyApplication)
            modules(appModule)
        }
    }
}