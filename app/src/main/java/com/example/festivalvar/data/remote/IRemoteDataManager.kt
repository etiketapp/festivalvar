package com.example.festivalvar.data.remote

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.network.ResultWrapper

interface IRemoteDataManager {
    suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse>
    suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse>
    suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse>
    suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse>

}