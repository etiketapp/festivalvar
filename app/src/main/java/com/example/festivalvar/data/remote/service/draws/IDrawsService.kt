package com.example.festivalvar.data.remote.service.draws

import com.example.festivalvar.data.remote.model.draws.DrawsModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface IDrawsService {


    @GET("draws")
    fun getDraws(): Deferred<Response<DrawsModelResponse>>
}
