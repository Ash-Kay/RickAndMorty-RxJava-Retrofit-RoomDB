package com.example.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.utils.TypeConverter

@Database(entities = arrayOf(Character::class), version = 1)
@TypeConverters(TypeConverter::class)
abstract class CharacterDatabase : RoomDatabase(){

    abstract fun characterDao() : CharacterDao

    companion object {
        private var INSTANCE: CharacterDatabase? = null

        fun getInstance(context: Context): CharacterDatabase? {
            if (INSTANCE == null) {
                synchronized(CharacterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CharacterDatabase::class.java, "character_database")
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