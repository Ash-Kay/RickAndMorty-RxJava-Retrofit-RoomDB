package com.example.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickandmorty.models.Episode

@Dao
interface EpisodeDao {
    @Insert
    fun insert(note : Episode)

    @Query("DELETE FROM episode_table")
    fun deleteAll()

    @Query("SELECT * FROM episode_table ORDER BY id")
    fun getAll() : LiveData<List<Episode>>
}