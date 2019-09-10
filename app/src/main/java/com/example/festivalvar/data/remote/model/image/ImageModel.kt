package com.example.festivalvar.data.remote.model.image

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ImageModel (

    @SerializedName("width")
    @Expose
    val width: Int? = null,
    @SerializedName("height")
    @Expose
    val height: Int? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null
) : Parcelable