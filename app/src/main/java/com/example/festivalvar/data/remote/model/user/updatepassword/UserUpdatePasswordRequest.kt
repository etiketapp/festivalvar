package com.example.festivalvar.data.remote.model.user.updatepassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserUpdatePasswordRequest (
    @SerializedName("password")
    @Expose
    val password: String?,

    @SerializedName("new_password")
    @Expose
    val new_password: String?,

    @SerializedName("password_confirmation")
    @Expose
    val password_confirmation: String?
)