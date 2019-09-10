package com.example.festivalvar.data.remote.model.auth.register

import com.google.gson.annotations.SerializedName

class RegisterRequest (

    @SerializedName("full_name")
    val full_name : String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    var token : String,
    @SerializedName("provider")
    val provider:String
)