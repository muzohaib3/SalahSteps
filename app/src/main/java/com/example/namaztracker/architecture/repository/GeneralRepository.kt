package com.example.namaztracker.architecture.repository

import com.example.namaztracker.model.SalahDataModel

interface GeneralRepository {

    fun saveSalah(salahDataModel: SalahDataModel): Long

    fun updateSalah(fajr:Int , zuhr:Int, asr:Int , maghrib:Int, isha :Int, id:Int):Int

}