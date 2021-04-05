package com.example.mynote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class Note(
    @SerialName("title")
    var title:String,
    @SerialName("url")
    var url:String,
    @SerialName("description")
    var description :String): Parcelable

