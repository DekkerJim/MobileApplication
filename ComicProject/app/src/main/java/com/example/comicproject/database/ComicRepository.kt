package com.example.comicproject.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.comicproject.model.entity.Comic

class ComicRepository(context : Context) {

    private var comicDao: ComicDao

    init {
        val database = ComicRoomDatabase.getDatabase(context)
        comicDao = database!!.comicDao()
    }

    fun getAllComics(): LiveData<List<Comic>> {
        return comicDao.getAllComics()
    }

    fun insertComic(comic: Comic) {
        comicDao.insertComic(comic)
    }

    fun deleteComic(comic: Comic) {
        comicDao.deleteComic(comic)
    }

    fun updateComic(comic: Comic) {
        comicDao.updateComic(comic)
    }

    suspend fun deleteAllComics(){
        comicDao.deleteAllComics()
    }

}