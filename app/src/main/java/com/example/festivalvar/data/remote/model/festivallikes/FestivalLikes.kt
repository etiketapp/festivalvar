package com.example.festivalvar.data.remote.model.festivallikes

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FestivalLikes (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("is_liked")
    @Expose
    val is_liked: Boolean? = null,
    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("user")
    @Expose
    val user: User? = null
) : Parcelable