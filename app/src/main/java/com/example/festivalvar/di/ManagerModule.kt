package com.example.festivalvar.di

import com.example.festivalvar.data.local.LocalDataManager
import com.example.festivalvar.data.remote.RemoteDataManager
import com.example.festivalvar.data.repository.DataManager
import org.koin.dsl.module.module

val managerModule = module {
    single { RemoteDataManager(get(), get(), get()) }
    single { LocalDataManager() }
    single { DataManager(get(), get()) }
}