package com.example.festivalvar.data.remote.service.user

import com.example.festivalvar.data.remote.model.user.UserResponse
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdateResponse
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IUserService {


    @GET("user/me")
    fun getUserMe(): Deferred<Response<UserResponse>>

    @Multipart
    @POST("user/me")
    fun postUserUpdate(
        @Part("_method") _method: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("full_name") full_name: RequestBody,
        @Part("email") email: RequestBody
    ): Deferred<Response<UserUpdateResponse>>


}