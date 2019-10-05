package com.example.festivalvar.data.remote.model.messages.sendmodel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MessageSendModel (
    @SerializedName("message")
    @Expose
    val message: String? = null,
    @SerializedName("user_one_id")
    @Expose
    val user_one_id: Int? = null,
    @SerializedName("user_two_id")
    @Expose
    val user_two_id: Int? = null,
    @SerializedName("conversation_id")
    @Expose
    val conversation_id: Int? = null,
    @SerializedName("date")
    @Expose
    val date: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null
) : Parcelable
