package com.example.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.models.Episode
import com.example.rickandmorty.utils.TypeConverter

@Database(entities = arrayOf(Episode::class), version = 1)
@TypeConverters(TypeConverter::class)
abstract class EpisodeDatabase: RoomDatabase() {

    abstract fun episodeDao() : EpisodeDao

    companion object {
        private var INSTANCE: EpisodeDatabase? = null

        fun getInstance(context: Context): EpisodeDatabase? {
            if (INSTANCE == null) {
                synchronized(EpisodeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        EpisodeDatabase::class.java, "episode_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }

}