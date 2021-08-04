package com.example.mvvmusingjetpack.di

import android.app.Application
import com.example.mvvmusingjetpack.di.appModule
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}