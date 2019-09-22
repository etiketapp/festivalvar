package com.example.festivalvar.data.remote.model.comments

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.image.ImageModel
import com.example.festivalvar.data.remote.model.location.LocationModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FestivalCommentsUser (

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
    @SerializedName("category_id")
    @Expose
    val category_id: Int? = null,
    @SerializedName("likes_count")
    @Expose
    val likes_count: Int? = null,
    @SerializedName("comments_count")
    @Expose
    val comments_count: Int? = null,
    @SerializedName("is_liked")
    @Expose
    val is_liked: Boolean? = null,
    @SerializedName("distance")
    @Expose
    val distance: Double?=null,
    @SerializedName("comments")
    @Expose
    val comments: ArrayList<CommentsModel>?=null
    ) : Parcelable