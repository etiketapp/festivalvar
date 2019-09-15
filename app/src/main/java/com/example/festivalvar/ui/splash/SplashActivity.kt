package com.example.festivalvar.ui.splash

import com.example.festivalvar.ui.auth.LoginRegisterActivity
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.main.MainActivity
import com.example.festivalvar.utils.AppConstants
import com.example.festivalvar.utils.PrefUtils
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity(), ISplashNavigator {

    var code: Int = 0

    override val layoutId: Int?
        get() = null
    private val viewModel by viewModel<SplashViewModel>()



    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        GlobalScope.launch {

            delay(AppConstants.DELAY_TIME)
            if(!PrefUtils.isLoggedUser()) {
                launchActivity<LoginRegisterActivity> { }
            } else {
                launchActivity<MainActivity> {  }
            }
        }

    }

    override fun initListener() {
    }


}
