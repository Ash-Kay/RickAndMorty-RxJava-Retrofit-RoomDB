package com.example.rickandmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val type : String,
    val dimension : String,
    val residents : List<String>,
    val url : String,
    val created : String
)

/*
data class ResidentsList(
    val residents: List<Character>
)*/
