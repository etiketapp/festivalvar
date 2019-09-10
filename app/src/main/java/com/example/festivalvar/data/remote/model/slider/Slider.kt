package com.example.festivalvar.data.remote.model.slider

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slider(
    @SerializedName("image")
    @Expose
    val sliderImage: Int
):Parcelable
