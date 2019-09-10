package com.example.festivalvar.data.remote.model.addressmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddressModel (
    val address: String,
    val city: City,
    val county: County

) : Parcelable