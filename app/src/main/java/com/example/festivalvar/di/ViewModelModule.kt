package com.example.festivalvar.di

import com.example.festivalvar.ui.auth.LoginRegisterViewModel
import com.example.festivalvar.ui.comments.CommentsViewModel
import com.example.festivalvar.ui.draws.DrawFragmentViewModel
import com.example.festivalvar.ui.draws.drawsdetail.DrawsDetailViewModel
import com.example.festivalvar.ui.festivaldetail.FestivalDetailViewModel
import com.example.festivalvar.ui.home.HomeViewModel
import com.example.festivalvar.ui.main.MainViewModel
import com.example.festivalvar.ui.messages.MessagesFragmentViewModel
import com.example.festivalvar.ui.notifications.NotificationsViewModel
import com.example.festivalvar.ui.profile.ProfileViewModel
import com.example.festivalvar.ui.profile.profilesettings.ProfileSettingsViewModel
import com.example.festivalvar.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginRegisterViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MessagesFragmentViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { FestivalDetailViewModel(get()) }
    viewModel { CommentsViewModel(get()) }
    viewModel { ProfileSettingsViewModel(get()) }
    viewModel { DrawFragmentViewModel(get()) }
    viewModel { DrawsDetailViewModel(get()) }


}