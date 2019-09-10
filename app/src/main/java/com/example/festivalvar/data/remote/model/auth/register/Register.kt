package com.example.festivalvar.data.remote.model.auth.register

import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.SerializedName

class Register (

    @SerializedName("token")
    val token:String,

    @SerializedName("user")
    val user: User
)