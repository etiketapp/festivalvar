package com.example.festivalvar.data.remote.model.comments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostCommentModelRequest (

    @SerializedName("festival_id")
    @Expose
    val festival_id: Int? = null,
    @SerializedName("comment")
    @Expose
    val comment: String? = null
)