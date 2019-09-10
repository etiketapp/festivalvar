package com.example.festivalvar

import android.app.Application
import android.content.Context
import com.example.festivalvar.di.appModule
import org.koin.android.ext.android.startKoin



class CoreApp : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureDi()
        //Fabric.with(this, Crashlytics())
    }

    private fun configureDi() = startKoin(this, appModule)

}