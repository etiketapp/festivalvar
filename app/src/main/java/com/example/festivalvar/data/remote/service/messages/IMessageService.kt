package com.example.festivalvar.data.remote.service.messages

import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.model.messages.MessageIndexResponse
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelRequest
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IMessageService {


    @POST("message/send")
    fun postSendMessage(@Body request: MessageSendModelRequest): Deferred<Response<MessageSendModelResponse>>


    @GET("message")
    fun getMessageIndex(): Deferred<Response<MessageIndexResponse>>
}