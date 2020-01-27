package com.example.comicproject.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Data (
    @SerializedName("id") var id: Int,
    @SerializedName("attributes") var attributes: @RawValue Attribute
) : Parcelable {
    fun getPosterUrl() = "https://media.kitsu.io/manga/poster_images/$id/small.jpg";
}