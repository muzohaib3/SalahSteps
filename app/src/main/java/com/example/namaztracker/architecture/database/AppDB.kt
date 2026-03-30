package com.example.namaztracker.architecture.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.namaztracker.model.SalahDataModel

@Database(entities = [SalahDataModel::class], version = 2, exportSchema = false)
abstract class AppDB : RoomDatabase()
{
    abstract fun appDAO()    : AppDao

    companion object
    {
        private lateinit var INSTANCE: AppDB

        fun getFileDatabase(context: Context): AppDB
        {
            if (!this::INSTANCE.isInitialized)
            {
                INSTANCE = Room
                    .databaseBuilder(context, AppDB::class.java, "SalahTracker")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE
        }
    }
}