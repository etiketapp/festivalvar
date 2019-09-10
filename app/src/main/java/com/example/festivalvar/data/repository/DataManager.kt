package com.example.festivalvar.data.repository

import com.example.festivalvar.data.local.LocalDataManager
import com.example.festivalvar.data.remote.RemoteDataManager
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.network.ResultWrapper

class DataManager(
    private val remoteDataManager: RemoteDataManager,
    private val localDataManager: LocalDataManager
) : IDataManager {

    override suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse> = remoteDataManager.getFestivalAsync()

    override suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse> =
        remoteDataManager.postLoginAsync(request)

    override suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse> =
        remoteDataManager.postRegisterAsync(request)


    override suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse> =
        remoteDataManager.postForgotPasswordAsync(request)

}