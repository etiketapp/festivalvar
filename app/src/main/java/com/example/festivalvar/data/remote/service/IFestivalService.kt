package com.example.festivalvar.data.remote.service

import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface IFestivalService {


    @GET("festival")
    fun getFestivals(): Deferred<Response<FestivalModelResponse>>
}