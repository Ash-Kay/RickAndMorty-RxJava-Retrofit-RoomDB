package com.example.rickandmorty.viewmodel

import android.app.Application
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.repository.CharacterRepository
import io.reactivex.Observable


class CharacterViewModel(application: Application, pageNo: Int){

    private val characterRepository = CharacterRepository(application, pageNo)

    fun getCharacters() : Observable<List<Character>>{
        return characterRepository.getCharacters()
    }

    fun clearCharacterDisposable(){
        characterRepository.clearDisposable()
    }


}