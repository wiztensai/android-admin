package com.jetwiz.admin.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.jetwiz.admin.ModelBarang
import com.jetwiz.admin.base.BaseApp

@Database(entities = arrayOf(ModelBarang::class),
    version = 1, exportSchema = false)
abstract class DB_General: RoomDatabase() {

    abstract fun barangDao(): BarangDao

    companion object {
        private val DB_NAME="database-general"
        private var INSTANCE: DB_General? = null

        fun getAppDatabase(context: Context): DB_General {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext as BaseApp,
                    DB_General::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE as DB_General
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}