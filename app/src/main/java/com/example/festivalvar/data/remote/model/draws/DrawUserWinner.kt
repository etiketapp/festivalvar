package com.example.festivalvar.data.remote.model.draws

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DrawUserWinner (

    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("updated_date")
    @Expose
    val updated_date: String? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("draw_id")
    @Expose
    val draw_id: Int? = null
) : Parcelable
