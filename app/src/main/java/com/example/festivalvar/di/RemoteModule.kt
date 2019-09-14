package com.example.festivalvar.di

import com.example.festivalvar.data.remote.service.IAuthService
import com.example.festivalvar.data.remote.service.IFestivalService
import com.example.festivalvar.data.remote.service.ServiceClient.createWebService
import com.example.festivalvar.data.remote.service.user.IUserService
import org.koin.dsl.module.module

val remoteModule = module {
   factory { createWebService<IAuthService>() }
   factory { createWebService<IFestivalService>() }
   factory { createWebService<IUserService>() }
}