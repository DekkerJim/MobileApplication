package com.example.comicproject.api

class ComicRepository {
    private val comicApi: ComicApiService = ComicApi.createApi()

    fun getComics(textInput: String) = comicApi.getComics(textInput)
}