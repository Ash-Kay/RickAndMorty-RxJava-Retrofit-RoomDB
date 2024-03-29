package com.example.rickandmorty.database

import androidx.room.*
import com.example.rickandmorty.models.Character
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CharacterDao {
    @Insert
    fun insert(note : Character)

    @Query("DELETE FROM character_table")
    fun deleteAll()

    @Query("SELECT * FROM character_table")
    fun getAll() : Flowable<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Character>)
}