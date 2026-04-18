package com.example.namaztracker.architecture.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.namaztracker.model.NamazItemModel
import com.example.namaztracker.model.SalahDataModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSalah(salahDataModel: SalahDataModel): Long

    @Query("SELECT * FROM salah_record WHERE date = :date")
    fun getRecordAccDate(date:String):SalahDataModel?

    @Query("""
    SELECT 
    SUM(
        (CASE WHEN fajr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN zuhr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN asr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN maghrib = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN isha = 0 THEN 1 ELSE 0 END)
    ) 
    FROM salah_record
""")
    fun getTotalMissedNamazCount(): Int

    @Query("""
    SELECT * FROM salah_record WHERE (CASE WHEN fajr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN zuhr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN asr = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN maghrib = 0 THEN 1 ELSE 0 END) +
        (CASE WHEN isha = 0 THEN 1 ELSE 0 END)
""")
    fun getTotalMissedNamaz(): List<SalahDataModel>


    @Query("UPDATE salah_record SET fajr = :fajr ,zuhr = :zuhr ,asr = :asr, maghrib = :maghrib, isha = :isha WHERE id = :id;")
    fun updateSalah(fajr:Int , zuhr:Int, asr:Int , maghrib:Int, isha :Int, id:Int): Int

}