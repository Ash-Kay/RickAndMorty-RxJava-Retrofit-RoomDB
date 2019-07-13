package com.example.rickandmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.utils.TypeConverter

@Entity(tableName = "character_table")
data class Character(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin	: ShortLocation,
    val location: ShortLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

/*data class EpisodeList(
    val episode: ArrayList<String>
)*/

data class ShortLocation(
    val name: String,
    val url: String
)