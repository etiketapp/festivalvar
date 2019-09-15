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
    val id: Int? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("sub_title")
    @Expose
    val sub_title: String? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("last_date")
    @Expose
    val last_date: String? = null,
    @SerializedName("is_joined")
    @Expose
    val is_joined: Boolean? = null,
    @SerializedName("draw_users_count")
    @Expose
    val draw_users_count: Int? = null,
    @SerializedName("image")
    @Expose
    val image: ImageModel? = null,
    @SerializedName("galleries")
    @Expose
    val galleries: ArrayList<Galleries>? = null,
    @SerializedName("draw_user_winner")
    @Expose
    val draw_user_winner: DrawUserWinner? = null
) : Parcelable
