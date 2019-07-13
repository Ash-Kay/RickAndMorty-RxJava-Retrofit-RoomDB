package com.example.rickandmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class Episode(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val air_date : String,
    val episode : String,
    val characters : List<String>,
    val url : String,
    val created : String
)

/*
data class CharactersList(
    val characters: List<Character>
)*/
