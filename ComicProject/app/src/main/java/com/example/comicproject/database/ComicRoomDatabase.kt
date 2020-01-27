package com.example.comicproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comicproject.model.api.Data
import com.example.comicproject.model.entity.Comic

@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class ComicRoomDatabase : RoomDatabase() {

    abstract fun comicDao(): ComicDao

    companion object {
        private const val DATABASE_NAME = "COMIC_DATABASE"

        @Volatile
        private var comicRoomDatabaseInstance: ComicRoomDatabase? = null

        fun getDatabase(context: Context): ComicRoomDatabase? {
            if (comicRoomDatabaseInstance == null) {
                synchronized(ComicRoomDatabase::class.java) {
                    if (comicRoomDatabaseInstance == null) {
                        comicRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ComicRoomDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return comicRoomDatabaseInstance
        }
    }

}
