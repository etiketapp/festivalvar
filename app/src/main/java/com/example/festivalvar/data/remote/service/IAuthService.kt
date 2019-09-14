package com.example.festivalvar.data.remote.service

import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.logout.LogoutResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthService {

    @POST("auth/register")
    fun postRegister(@Body request: RegisterRequest): Deferred<Response<RegisterResponse>>


    @POST("auth/login")
    fun postLogin(@Body request: LoginRequest): Deferred<Response<LoginResponse>>


    @POST("auth/forgot")
    fun postForgotPassword(@Body request: ForgotPasswordRequest): Deferred<Response<ForgotPasswordResponse>>


    @POST("auth/logout")
    fun postLogout(): Deferred<Response<LogoutResponse>>

}