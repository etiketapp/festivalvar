package com.example.festivalvar.data.remote.model.FestivalModel

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.addressmodel.AddressModel
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.example.festivalvar.data.remote.model.location.LocationModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FestivalModel (

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("sub_title")
    @Expose
    val sub_title: String,
    @SerializedName("advice")
    @Expose
    val advice: String,
    @SerializedName("place")
    @Expose
    val place: String,
    @SerializedName("price")
    @Expose
    val price: String,
    @SerializedName("about")
    @Expose
    val about: String,
    @SerializedName("location")
    @Expose
    val location: LocationModel,
    @SerializedName("start_date")
    @Expose
    val start_date: String,
    @SerializedName("end_date")
    @Expose
    val end_date: String,
    @SerializedName("category_id")
    @Expose
    val category_id: Int,
    @SerializedName("distance")
    @Expose
    val distance: Double?=null,
    @SerializedName("image")
    @Expose
    val image: ImageModel?=null,
    @SerializedName("address")
    @Expose
    val address: AddressModel,
    @SerializedName("category")
    @Expose
    val category: CategoryModel,
    @SerializedName("galleries")
    @Expose
    val galleries: ArrayList<Galleries>

) : Parcelable
