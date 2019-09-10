package com.example.festivalvar.ui.auth

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.CoreApp.Companion.context
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPassword
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.login.Login
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.register.Register
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.user.User
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.main.MainActivity
import com.example.festivalvar.ui.base.BaseViewModel
import com.example.festivalvar.utils.PrefUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginRegisterViewModel(dataManager: DataManager): BaseViewModel<ILoginRegisterNavigator>(dataManager) {


    val loginData: MutableLiveData<Login> = MutableLiveData()
    val forgotPasswordData: MutableLiveData<ForgotPassword> = MutableLiveData()
    val userMeData: MutableLiveData<User> = MutableLiveData()

    val registerData: MutableLiveData<Register> = MutableLiveData()

    fun getRegister(request: RegisterRequest) {

        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO)
            { dataManager.postRegisterAsync(request) }) {

                is ResultWrapper.Success -> {

                    registerData.value = result.data.data

                    PrefUtils.createUser(GsonBuilder().serializeNulls().create().toJson(result.data.data!!.user))
                    PrefUtils.createToken(result.data.data.token)
                    getNavigator()?.hideLoading()
                    context.launchActivity<MainActivity> {}


                }

                is ResultWrapper.Error -> {

                    getNavigator()?.hideLoading()

                    getNavigator()?.onError(result.exception.message, result.exception.code)

                }

            }

        }

    }

    fun getLogin(request: LoginRequest) {

        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO) { dataManager.postLoginAsync(request) }) {

                is ResultWrapper.Success -> {

                    loginData.value = result.data.data

                    getNavigator()?.hideLoading()
                    PrefUtils.createUser(Gson().toJson(result.data.data!!.user))
                    PrefUtils.createToken(result.data.data.token)
                    context.launchActivity<MainActivity> {}

                }

                is ResultWrapper.Error -> {

                    getNavigator()?.hideLoading()
                    getNavigator()?.onError(result.exception.message, result.exception.code)

                }

            }

        }

    }

    fun getForgotPassword(request: ForgotPasswordRequest) {

        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO) { dataManager.postForgotPasswordAsync(request) }) {

                is ResultWrapper.Success -> {

                    forgotPasswordData.value = result.data.data

                    getNavigator()?.hideLoading()


                }

                is ResultWrapper.Error -> {

                    getNavigator()?.hideLoading()

                    getNavigator()?.onError(result.exception.message, result.exception.code)

                }

            }

        }

    }



}