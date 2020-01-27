package com.example.comicproject.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "comicTable")
data class Comic(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "slug")
    var slug : String

    //@ColumnInfo(name = "date")
    //var date : Date

    //@ColumnInfo(name = "date")
    //var date : Date

    //@ColumnInfo(name = "synopsis")
    //var synopsis : String

) : Parcelable