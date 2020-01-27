package com.example.comicproject.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Attribute (
    @SerializedName("slug") var slug: String,
    @SerializedName("synopsis") var synopsis: String,
    @SerializedName("startDate") var startDate: Date,
    @SerializedName("endDate") var endDate: Date
) : Parcelable