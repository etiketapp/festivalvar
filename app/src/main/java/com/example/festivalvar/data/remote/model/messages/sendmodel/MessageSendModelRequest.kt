package com.example.festivalvar.data.remote.model.messages.sendmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageSendModelRequest (
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("user_two")
    @Expose
    val user_two: Int
)