package com.meeweel.delivery.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.meeweel.delivery.di.application
import com.meeweel.delivery.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder { lateinit var context: Context }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this


        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen))
        }
    }
}