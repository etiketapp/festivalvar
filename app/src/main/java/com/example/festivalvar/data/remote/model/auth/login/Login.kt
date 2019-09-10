package com.example.festivalvar.data.remote.model.auth.login

import com.example.festivalvar.data.remote.model.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login (

    @SerializedName("token")
    @Expose
    var token:String,
    @SerializedName("user")
    @Expose
    var user: User
)