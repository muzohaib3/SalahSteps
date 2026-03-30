package com.example.namaztracker.architecture

import com.example.namaztracker.model.SalahDataModel

interface GeneralRepository {

    fun saveSalah(salahDataModel: SalahDataModel)

}