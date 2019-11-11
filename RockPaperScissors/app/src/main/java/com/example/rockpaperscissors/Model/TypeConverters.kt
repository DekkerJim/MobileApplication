package com.example.rockpaperscissors.Model

import androidx.room.TypeConverter
import java.util.*

class TypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toOrdinal(choice: Choice): Int {
        return choice.ordinal
    }

    @TypeConverter
    fun toPlay(ordinal: Int): Choice?{
        return Choice.values().first {it.ordinal == ordinal}
    }

    @TypeConverter
    fun toResult(ordinal: Int): Result?{
        return Result.values().first {it.ordinal == ordinal}
    }

    @TypeConverter
    fun toString(result: Result): Int {
        return result.ordinal
    }

}


