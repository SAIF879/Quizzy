package com.example.quizzy.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
}

