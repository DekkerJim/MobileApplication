package com.example.comicproject.model.api

import com.google.gson.annotations.SerializedName

data class Poster (
    @SerializedName("small") var small: String
    //@SerializedName("original") var original: String
)