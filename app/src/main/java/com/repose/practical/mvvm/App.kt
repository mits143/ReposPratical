package com.repose.practical.mvvm

import android.app.Application
import com.repose.practical.mvvm.di.module.appModule
import com.repose.practical.mvvm.di.module.repoModule
import com.repose.practical.mvvm.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



val sessionManager: AppPreference by lazy {
    App.sharedPreference
}

class App : Application() {
    companion object {
        lateinit var sharedPreference: AppPreference
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreference = AppPreference(this@App)
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}
