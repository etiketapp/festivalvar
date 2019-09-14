package com.example.festivalvar.data.remote

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.logout.LogoutResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.model.user.UserResponse
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdateResponse
import com.example.festivalvar.data.remote.network.ResultWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IRemoteDataManager {

    /** Auth **/
    suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse>
    suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse>
    suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse>
    suspend fun postLogoutAsync(): ResultWrapper<LogoutResponse>

    /** User **/
    suspend fun getUserMeAsync(): ResultWrapper<UserResponse>
    suspend fun updateProfileAsync(
        _method: RequestBody,
        image: MultipartBody.Part?,
        full_name: RequestBody,
        email: RequestBody
    ): ResultWrapper<UserUpdateResponse>

    /** Festival**/
    suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse>

}