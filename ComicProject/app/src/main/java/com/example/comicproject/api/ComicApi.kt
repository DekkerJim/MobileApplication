package com.example.comicproject.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "http://kitsu.io/"


        fun createApi(): ComicApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val numbersApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit NumbersApiService
            return numbersApi.create(ComicApiService::class.java)
        }
    }

}