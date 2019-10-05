package com.example.festivalvar.data.remote.model.messages

import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModel
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageIndex (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("user_one_id")
    @Expose
    val user_one_id: Int? = null,
    @SerializedName("user_two_id")
    @Expose
    val user_two_id: Int? = null,
    @SerializedName("latest_message")
    @Expose
    val latest_message: MessageSendModel? = null,
    @SerializedName("user_one")
    @Expose
    val user_one: User? = null,
    @SerializedName("user_two")
    @Expose
    val user_two: User? = null
)