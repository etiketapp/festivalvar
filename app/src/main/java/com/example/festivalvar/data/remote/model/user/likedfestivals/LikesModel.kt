package com.example.festivalvar.data.remote.model.user.likedfestivals

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LikesModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("like")
    @Expose
    val like: Int? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null

) : Parcelable