package com.example.namaztracker.architecture.repository

import com.example.namaztracker.architecture.database.AppDao
import com.example.namaztracker.model.SalahDataModel

class GeneralRepositoryImpl(val appDao: AppDao) :GeneralRepository {

    override fun saveSalah(salahDataModel: SalahDataModel) = appDao.insertSalah(salahDataModel)

    override fun updateSalah(fajr:Int , zuhr:Int, asr:Int , maghrib:Int, isha :Int, id:Int) =
        appDao.updateSalah(fajr, zuhr, asr, maghrib, isha, id)

}