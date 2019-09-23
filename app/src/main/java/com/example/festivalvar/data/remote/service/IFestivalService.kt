package com.example.festivalvar.data.remote.service

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUserResponse
import com.example.festivalvar.data.remote.model.comments.PostCommentModelRequest
import com.example.festivalvar.data.remote.model.comments.PostCommentModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IFestivalService {


    @GET("festival")
    fun getFestivals(): Deferred<Response<FestivalModelResponse>>


    @GET("festival/{id}/commentUsers")
    fun getUserComments(@Path("id") festivalId: Int): Deferred<Response<FestivalCommentsUserResponse>>


    @GET("festival/{id}/likeUsers")
    fun getFestivalLikes(@Path("id") festivalId: Int): Deferred<Response<FestivalLikesModelResponse>>


    @POST("festival/comment")
    fun postFestivalComment(@Body request: PostCommentModelRequest): Deferred<Response<PostCommentModelResponse>>
}