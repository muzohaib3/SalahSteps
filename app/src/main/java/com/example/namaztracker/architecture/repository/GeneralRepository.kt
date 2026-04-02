package com.example.namaztracker.architecture.repository

import com.example.namaztracker.model.SalahDataModel

interface GeneralRepository {

    fun saveSalah(salahDataModel: SalahDataModel)

    fun updateSalah(id: Int, fajr: Int, zuhr: Int, asr: Int, maghrib: Int, isha: Int, date: String)

}