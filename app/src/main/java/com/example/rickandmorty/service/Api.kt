package com.example.rickandmorty.service

import com.example.rickandmorty.models.Character
import com.example.rickandmorty.response.AllCharacterResponse
import com.example.rickandmorty.response.CharacterResponse
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getAllCharacter(
        @Query("page") page: String?
    ): Observable<AllCharacterResponse>

    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: String
    ): Observable<Character>

    @GET("character")
    fun getCharacterFiltered(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("gender") gender: String
    ): Observable<AllCharacterResponse>
}