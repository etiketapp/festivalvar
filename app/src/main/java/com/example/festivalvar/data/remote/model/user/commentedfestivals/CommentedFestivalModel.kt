package com.example.festivalvar.data.remote.model.user.commentedfestivals

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CommentedFestivalModel (

    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("comment")
    @Expose
    val comment: String? =  null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("festival")
    @Expose
    val festival: FestivalModel? = null
) : Parcelable
