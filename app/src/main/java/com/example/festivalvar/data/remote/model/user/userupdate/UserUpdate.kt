package com.example.festivalvar.data.remote.model.user.userupdate

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserUpdate (
    @SerializedName("id")
    @Expose
    val id:Int,
    @SerializedName("email")
    @Expose
    val email:String,
    @SerializedName("full_name")
    @Expose
    val full_name:String,
    @SerializedName("birth_date")
    @Expose
    val birth_date:String,
    @SerializedName("created_at")
    @Expose
    val created_at: String,
    @SerializedName("deleted_at")
    @Expose
    val deleted_at: String,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String,
    @SerializedName("image")
    @Expose
    val image: ImageModel? = null
) : Parcelable