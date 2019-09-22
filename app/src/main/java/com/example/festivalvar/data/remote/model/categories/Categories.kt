package com.example.festivalvar.data.remote.model.categories

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Categories (

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
    val created_at: String,
    @SerializedName("festivals")
    @Expose
    val festivals: ArrayList<Festivals>? = null,
    @SerializedName("image")
    @Expose
    val image: ImageModel
) : Parcelable