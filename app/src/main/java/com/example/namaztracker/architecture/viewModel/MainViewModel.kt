package com.example.namaztracker.architecture.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.architecture.repository.GeneralRepositoryImpl
import com.example.namaztracker.model.SalahDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GeneralRepositoryImpl

    init {
        val dao = AppDB.getFileDatabase(application).appDAO()
        repository = GeneralRepositoryImpl(dao)
    }

    var fajrSalah:Int = 0
    var zuhrSalah:Int = 0
    var asrSalah:Int = 0
    var maghribSalah:Int = 0
    var ishaSalah:Int = 0

    var isFChk = false
    var isZChk = false
    var isAChk = false
    var isMChk = false
    var isIChk = false


    fun updateSalah(fajr:Int , zuhr:Int, asr:Int , maghrib:Int, isha :Int, id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSalah(fajr, zuhr, asr, maghrib, isha, id)
        }
    }

}