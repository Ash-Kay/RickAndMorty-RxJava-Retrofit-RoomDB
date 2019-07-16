package com.example.rickandmorty.response

import com.example.rickandmorty.models.Character


data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

//Character Response

data class AllCharacterResponse(
    val info: Info,
    val results: List<Character>
)

data class CharacterResponse(
    val character: Character
)
