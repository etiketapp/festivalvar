package com.example.festivalvar.data.remote.model.messages.messagedetail

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MessageDetailModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("user_one_id")
    @Expose
    val user_one_id: Int? = null,
    @SerializedName("user_two_id")
    @Expose
    val user_two_id: Int? = null,
    @SerializedName("message")
    @Expose
    val message: String? = null,
    @SerializedName("conversation_id")
    @Expose
    val conversation_id: Int? = null
) : Parcelable