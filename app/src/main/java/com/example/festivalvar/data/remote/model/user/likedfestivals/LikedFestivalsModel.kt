package com.example.festivalvar.data.remote.model.user.likedfestivals

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LikedFestivalsModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("like")
    @Expose
    val like: Int? =  null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null,
    @SerializedName("festival")
    @Expose
    val festival: FestivalModel? = null,
    @SerializedName("user")
    @Expose
    val user: User? = null,
    @SerializedName("likes")
    @Expose
    val likes: ArrayList<LikesModel>? = null
) : Parcelable


