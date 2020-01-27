package com.example.comicproject.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.comicproject.database.ComicRepository
import com.example.comicproject.model.entity.Comic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val comicRepository = ComicRepository(application.applicationContext)

    val comics: LiveData<List<Comic>> = comicRepository.getAllComics()

    fun deleteComics() {
        ioScope.launch {
            comicRepository.deleteAllComics()
        }
    }

    fun deleteComic(comic: Comic) {
        ioScope.launch {
            comicRepository.deleteComic(comic)
        }
    }
}