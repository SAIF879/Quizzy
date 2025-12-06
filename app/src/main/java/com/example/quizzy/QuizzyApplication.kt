package com.example.quizzy

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizzyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizzyApplication)

        }
    }

}