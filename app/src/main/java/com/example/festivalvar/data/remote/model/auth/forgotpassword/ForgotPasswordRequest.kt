package com.example.festivalvar.data.remote.model.auth.forgotpassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForgotPasswordRequest (

    @SerializedName("email")
    @Expose
    val email: String
)