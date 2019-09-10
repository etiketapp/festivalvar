package com.example.festivalvar.data.remote.model.location

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LocationModel (

    @SerializedName("latitude")
    @Expose
    val latitude: String,
    @SerializedName("longitude")
    @Expose
    val longitude: String

) : Parcelable