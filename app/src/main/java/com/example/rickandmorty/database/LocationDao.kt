package com.example.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickandmorty.models.Location

@Dao
interface LocationDao {
    @Insert
    fun insert(note : Location)

    @Query("DELETE FROM location_table")
    fun deleteAll()

    @Query("SELECT * FROM location_table ORDER BY id")
    fun getAll() : LiveData<List<Location>>
}