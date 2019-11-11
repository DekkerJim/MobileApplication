package com.example.rockpaperscissors.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game_table")
data class Game(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "result")
    var result: Result,

    @ColumnInfo(name = "aiMove")
    var aiMove: Choice,

    @ColumnInfo(name = "playerMove")
    var playerMove: Choice

) : Parcelable