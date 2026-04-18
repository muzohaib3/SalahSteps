package com.example.namaztracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "salah_record")
data class SalahDataModel(

    @ColumnInfo(name = "fajr")
    var fajr:Int = 0,
    @ColumnInfo(name = "zuhr")
    var zuhr:Int = 0,
    @ColumnInfo(name = "asr")
    var asr:Int = 0,
    @ColumnInfo(name = "maghrib")
    var maghrib:Int = 0,
    @ColumnInfo(name = "isha")
    var isha:Int = 0,
    @ColumnInfo(name = "date")
    var date:String = ""
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}