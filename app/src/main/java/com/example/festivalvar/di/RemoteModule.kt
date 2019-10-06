package com.example.festivalvar.di

import com.example.festivalvar.data.remote.service.IAuthService
import com.example.festivalvar.data.remote.service.IFestivalService
import com.example.festivalvar.data.remote.service.ServiceClient.createWebService
import com.example.festivalvar.data.remote.service.categories.ICategoryService
import com.example.festivalvar.data.remote.service.draws.IDrawsService
import com.example.festivalvar.data.remote.service.messages.IMessageService
import com.example.festivalvar.data.remote.service.user.IUserService
import com.example.festivalvar.ui.draws.drawsusersactivity.IUserDrawsNavigator
import org.koin.dsl.module.module

val remoteModule = module {
   factory { createWebService<IAuthService>() }
   factory { createWebService<IFestivalService>() }
   factory { createWebService<IUserService>() }
   factory { createWebService<IDrawsService>() }
   factory { createWebService<ICategoryService>() }
   factory { createWebService<IMessageService>() }
}