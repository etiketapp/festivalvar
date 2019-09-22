package com.example.festivalvar.data.remote.service

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IFestivalService {


    @GET("festival")
    fun getFestivals(): Deferred<Response<FestivalModelResponse>>


    @GET("festival/{id}/commentUsers")
    fun getUserComments(@Path("id") festivalId: Int): Deferred<Response<FestivalCommentsUserResponse>>
}