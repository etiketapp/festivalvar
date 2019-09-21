package com.example.festivalvar.ui.profile.profilesettings

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.CoreApp.Companion.context
import com.example.festivalvar.data.remote.model.auth.logout.Logout
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordRequest
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdate
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.auth.LoginRegisterActivity
import com.example.festivalvar.ui.base.BaseViewModel
import com.example.festivalvar.utils.PrefUtils
import com.google.gson.Gson
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProfileSettingsViewModel(dataManager: DataManager): BaseViewModel<IProfileSettingsNavigator>(dataManager) {


    val updateUserData: MutableLiveData<UserUpdate> = MutableLiveData()
    val logoutData: MutableLiveData<Logout> = MutableLiveData()


    @SuppressLint("LongLogTag")
    fun updateUser(
        method: RequestBody,
        multipartBody: MultipartBody.Part?,
        etFullname: RequestBody,
        etEmail: RequestBody
    ) {

        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO)

            {
                dataManager.updateProfileAsync(
                    method,
                    multipartBody,
                    etFullname,
                    etEmail
                )
            }) {

                is ResultWrapper.Success -> {
                    updateUserData.value = result.data.data
                    getNavigator()?.hideLoading()
                    getNavigator()?.onSucces(result.data.message)
                    PrefUtils.set(Gson().toJson(result.data.data))

                }

                is ResultWrapper.Error -> {
                    getNavigator()?.hideLoading()
                    getNavigator()?.onError(result.exception.message, result.exception.code)
                }
            }
        }

    }


    fun getLogout() {

        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO) { dataManager.postLogoutAsync() }) {

                is ResultWrapper.Success -> {

                    logoutData.value = result.data.data
                    context.launchActivity<LoginRegisterActivity> { }
                    getNavigator()?.hideLoading()
                    PrefUtils.logoutUser()

                }

                is ResultWrapper.Error -> {

                    getNavigator()?.hideLoading()
                    getNavigator()?.onError(result.exception.message, result.exception.code)

                }

            }

        }

    }

    fun updatePassword(passwordRequest: UserUpdatePasswordRequest, userId:Int) {
        getNavigator()?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            when (val result = withContext(Dispatchers.IO) { dataManager.putPaswwordUpdateAsync(passwordRequest,userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    getNavigator()?.onSucces(result.data.message)
                    getNavigator()?.succesChangePassword(result.data.message)
                }
                is ResultWrapper.Error -> {
                    getNavigator()?.hideLoading()
                    getNavigator()?.onError(result.exception.message,result.exception.code)
                }
            }

        }
    }

}