package com.example.comicproject.api

import com.example.comicproject.model.api.ComicApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApiService {
    @GET("/api/edge/manga")
    fun getComics(
        @Query("filter[text]=") textInput: String
    ): Call<ComicApiResponse>
}