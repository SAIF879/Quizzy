package com.example.quizzy

import android.app.Application
import com.example.quizzy.di.appModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizzyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@QuizzyApplication)
            modules(appModule)
        }
    }
}
