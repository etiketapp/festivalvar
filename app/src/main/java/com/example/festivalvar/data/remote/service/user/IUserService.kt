package com.example.festivalvar.data.remote.service.user

import com.example.festivalvar.data.remote.model.user.UserResponse
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordRequest
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordResponse
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdateResponse
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

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


    @PUT("user/{id}/password")
    fun putUserUpdatePassword(@Body request: UserUpdatePasswordRequest, @Path("id") userId: Int): Deferred<Response<UserUpdatePasswordResponse>>


}