package com.example.festivalvar.data.remote.model.user.draws

import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDraws (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("draw_id")
    @Expose
    val draw_id: Int? = null,
    @SerializedName("winner_id_first")
    @Expose
    val winner_id_first: Int? = null,
    @SerializedName("winner_id_second")
    @Expose
    val winner_id_second: Int? = null,
    @SerializedName("draw_users_count")
    @Expose
    val draw_users_count: Int? = null,
    @SerializedName("user")
    @Expose
    val user: User? = null,
    @SerializedName("draw")
    @Expose
    val draw: DrawsModel? = null
)

