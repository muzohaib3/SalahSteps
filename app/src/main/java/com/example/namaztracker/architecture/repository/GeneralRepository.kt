package com.example.namaztracker.architecture.repository

import com.example.namaztracker.model.SalahDataModel

interface GeneralRepository {

    fun saveSalah(salahDataModel: SalahDataModel)

    fun updateSalah(salahDataModel: SalahDataModel)

}