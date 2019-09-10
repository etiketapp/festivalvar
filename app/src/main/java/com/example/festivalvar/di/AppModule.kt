package com.example.festivalvar.di

import com.example.festivalvar.di.localModule
import com.example.festivalvar.di.managerModule
import com.example.festivalvar.di.remoteModule
import com.example.festivalvar.di.viewModelModule

val appModule = listOf(localModule, remoteModule, managerModule, viewModelModule)