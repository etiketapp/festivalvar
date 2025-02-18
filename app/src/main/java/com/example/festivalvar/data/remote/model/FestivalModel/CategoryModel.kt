package com.example.festivalvar.data.remote.model.FestivalModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryModel (

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String,
    @SerializedName("created_at")
    @Expose
    val created_at: String
) : Parcelable