package com.example.comicproject.model.api

import com.google.gson.annotations.SerializedName

data class ComicApiResponse (

    @SerializedName("data") var data: List<Data>
)