package com.example.festivalvar.data.remote.model.draws

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DrawsModel (

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("sub_title")
    @Expose
    val sub_title: String,
    @SerializedName("content")
    @Expose
    val content: String,
    @SerializedName("last_date")
    @Expose
    val last_date: String,
    @SerializedName("is_joined")
    @Expose
    val is_joined: Boolean,
    @SerializedName("draw_users_count")
    @Expose
    val draw_users_count: Int,
    @SerializedName("image")
    @Expose
    val image: ImageModel,
    @SerializedName("galleries")
    @Expose
    val galleries: ArrayList<Galleries>,
    @SerializedName("draw_user_winner")
    @Expose
    val draw_user_winner: DrawUserWinner
) : Parcelable
