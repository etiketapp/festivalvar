package com.example.festivalvar.data.remote.model.FestivalModel

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Galleries (

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String,
    @SerializedName("created_at")
    @Expose
    val created_at: String,
    @SerializedName("festival_id")
    @Expose
    val festival_id: String,
    @SerializedName("image")
    @Expose
    val image: ImageModel
) : Parcelable