package com.example.rickandmorty.repository

import android.annotation.SuppressLint
import android.app.Application
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterDatabase
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.response.AllCharacterResponse
import com.example.rickandmorty.service.Api
import com.example.rickandmorty.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterRepository (application: Application) {

    private var characterDao : CharacterDao
    //private var allCharacter : List<Character>
    //private var allCharacter : Single<List<Character>>

    init {
        val database : CharacterDatabase? = CharacterDatabase.getInstance(application)
        characterDao = database!!.characterDao()
        //allCharacter = characterDao.getAll()
    }

    fun getCharacters(): Observable<List<Character>> {
        return Observable.concatArray(
            getCharacterFromDb(),
            getCharacterFromApi())
    }

    /*fun getCharacterFromDb(): Observable<List<Character>>{
        return characterDao.getAll().filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                print("Getting  ${it.size} user form DB cache")
            }
    }*/

    fun getCharacterFromDb(): Observable<List<Character>>{
        return Observable.fromArray(characterDao.getAll())
            .filter { it.isNotEmpty() }
            .doOnNext {
                print("Getting  ${it.size} user form DB cache")
            }
    }

    fun getCharacterFromApi(): Observable<List<Character>>{
        return RetrofitInstance.api.getAllCharacter("0")
            .flatMap {
                Observable.fromArray(it.result)
            }
            .onErrorReturn {
                characterDao.getAll()
            }
            .doOnNext {
                storeCharactersInDb(it)
            }

    }

    fun getCharacterFromApi2(): Observable<AllCharacterResponse>{
        return RetrofitInstance.api.getAllCharacter("0")
            .doOnNext {
                print("Getting  ${it.result.size} user form Network")
            }

    }

    @SuppressLint("CheckResult")
    fun storeCharactersInDb(characters: List<Character>) {
        Observable.fromCallable { characterDao.insertAll(characters) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                print("Inserted ${characters.size} users from API in DB...")
            }
    }





}