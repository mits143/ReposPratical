package com.repose.practical.mvvm.di.module

import com.repose.practical.mvvm.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}