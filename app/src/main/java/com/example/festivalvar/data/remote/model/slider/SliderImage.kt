package com.example.festivalvar.data.remote.model.slider

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SliderImage(
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("url")
    @Expose
    val url: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SliderImage> {
        override fun createFromParcel(parcel: Parcel): SliderImage {
            return SliderImage(parcel)
        }

        override fun newArray(size: Int): Array<SliderImage?> {
            return arrayOfNulls(size)
        }
    }
}
