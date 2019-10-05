package com.example.festivalvar.data.remote.model.draws.join

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DrawsJoinModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("draw_id")
    @Expose
    val draw_id: Int? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("user")
    @Expose
    val user: User? = null,
    @SerializedName("draw")
    @Expose
    val draw: DrawsModel
) : Parcelable