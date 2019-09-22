package com.example.festivalvar.data.remote.model.user

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User (
    @SerializedName("id")
    @Expose
    val id:Int? = null,
    @SerializedName("email")
    @Expose
    val email:String? = null,
    @SerializedName("full_name")
    @Expose
    val full_name:String,
    @SerializedName("birth_date")
    @Expose
    val birth_date:String? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("deleted_at")
    @Expose
    val deleted_at: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
    @SerializedName("image")
    @Expose
    val image: ImageModel? = null
) : Parcelable

