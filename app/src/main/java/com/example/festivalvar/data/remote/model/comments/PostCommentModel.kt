package com.example.festivalvar.data.remote.model.comments

import android.os.Parcelable
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostCommentModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("comment")
    @Expose
    val comment: String? = null,
    @SerializedName("user_id")
    @Expose
    val user_id: Int? = null,
    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null,
    val user: User? = null
    ) : Parcelable