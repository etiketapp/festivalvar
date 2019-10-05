package com.example.festivalvar.data.remote.service

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUserResponse
import com.example.festivalvar.data.remote.model.comments.PostCommentModelRequest
import com.example.festivalvar.data.remote.model.comments.PostCommentModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesResponse
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikesModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface IFestivalService {


    @GET("festival")
    fun getFestivals(): Deferred<Response<FestivalModelResponse>>


    @GET("festival/{id}/commentUsers")
    fun getUserComments(@Path("id") festivalId: Int): Deferred<Response<FestivalCommentsUserResponse>>


    @GET("festival/{id}/likeUsers")
    fun getFestivalLikes(@Path("id") festivalId: Int): Deferred<Response<FestivalLikesModelResponse>>


    @POST("festival/comment")
    fun postFestivalComment(@Body request: PostCommentModelRequest): Deferred<Response<PostCommentModelResponse>>


    @GET("festival/like")
    fun getFestivalLikeAct(@Query("festival_id") festivalId: Int): Deferred<Response<FestivalLikesResponse>>


    @GET("festival/disLike")
    fun getFestivalDislikeAct(@Query("festival_id") festivalId: Int): Deferred<Response<FestivalLikesResponse>>
}