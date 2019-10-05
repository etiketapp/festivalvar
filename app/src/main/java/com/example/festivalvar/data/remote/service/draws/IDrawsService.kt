package com.example.festivalvar.data.remote.service.draws

import com.example.festivalvar.data.remote.model.draws.DrawsModelResponse
import com.example.festivalvar.data.remote.model.draws.join.DrawsJoinModelResponse
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.model.user.draws.UserDrawsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IDrawsService {


    @GET("draws")
    fun getDraws(): Deferred<Response<DrawsModelResponse>>


    @GET("draw/{id}/join")
    fun getDrawsJoin(@Path("id")drawId: Int): Deferred<Response<DrawsJoinModelResponse>>


    @GET("draw/{id}/disjoin")
    fun getDrawsDisjoin(@Path("id")drawId: Int): Deferred<Response<DrawsJoinModelResponse>>


    @GET("draw/{id}/users")
    fun getDrawsUser(@Path("id")drawId: Int): Deferred<Response<UserDrawsResponse>>
}
