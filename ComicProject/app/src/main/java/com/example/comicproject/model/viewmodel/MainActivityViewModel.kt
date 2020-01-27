package com.example.comicproject.model.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.comicproject.api.ComicRepository
import com.example.comicproject.model.api.ComicApiResponse
import com.example.comicproject.model.api.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val comicRepository = ComicRepository()
    val textInput = MutableLiveData<String>()
    val comics = MutableLiveData<List<Data>>().apply { value = ArrayList() }
    val error = MutableLiveData<String>()

    fun getComics() {
        comicRepository.getComics(textInput.value!!).enqueue(object : Callback<ComicApiResponse> {
            override fun onResponse(call: Call<ComicApiResponse>, response: Response<ComicApiResponse>) {
                if (response.isSuccessful){
                    comics.value = response.body()!!.data
                    //Log.i("Test123", comics.value.toString())
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<ComicApiResponse>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}