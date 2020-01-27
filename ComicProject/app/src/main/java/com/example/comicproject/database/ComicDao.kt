package com.example.comicproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.comicproject.model.entity.Comic

@Dao
interface ComicDao {

    @Query("SELECT * FROM comicTable")
    fun getAllComics(): LiveData<List<Comic>>

    @Insert
    fun insertComic(comic: Comic)

    @Delete
    fun deleteComic(comic: Comic)

    @Update
    fun updateComic(comic: Comic)

    @Query("DELETE FROM comicTable")
    suspend fun deleteAllComics()
}