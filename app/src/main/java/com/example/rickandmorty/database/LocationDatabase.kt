package com.example.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.models.Location
import com.example.rickandmorty.utils.TypeConverter


@Database(entities = arrayOf(Location::class), version = 1)
@TypeConverters(TypeConverter::class)
abstract class LocationDatabase : RoomDatabase(){

    abstract fun locationDao() : LocationDao

    companion object {
        private var INSTANCE: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase? {
            if (INSTANCE == null) {
                synchronized(LocationDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LocationDatabase::class.java, "location_database")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}