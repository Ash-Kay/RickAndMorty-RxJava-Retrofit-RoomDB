package com.example.rickandmorty.repository

import android.app.Application
import android.util.Log
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterDatabase
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.response.AllCharacterResponse
import com.example.rickandmorty.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterRepository (application: Application) {

    private var characterDao : CharacterDao
    //private var allCharacter : List<Character>
    //private var allCharacter : List<Character>

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

        return characterDao.getAll().toObservable()


        /*characterDao.getAll().
            subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Observable.fromArray(it)
                    .filter{ it.isNotEmpty() }
                    .doOnNext {
                        print("Getting  ${it.size} user form DB cache")
                    }
            },{
                Log.d("ashish", "Error ${it.message}")
            })*/

        /*return Observable.fromArray(characterDao.getAll())
            .filter { it.isNotEmpty() }
            .doOnNext {
                print("Getting  ${it.size} user form DB cache")
            }*/
    }

    fun getCharacterFromApi(): Observable<List<Character>>{
        return RetrofitInstance.api.getAllCharacter(null)
            .flatMap {
                Observable.fromArray(it.results)
            }
            .doOnError {
                getCharacterFromDb()
                Log.d("ashish","network error fetch from db")
            }
            .doOnNext {
                storeCharactersInDb(it)
            }

    }

    /*fun getCharacterFromApi2(): Observable<AllCharacterResponse>{
        return RetrofitInstance.api.getAllCharacter("0")
            .doOnNext {
                print("Getting  ${it.result.size} user form Network")
            }

    }*/

    fun storeCharactersInDb(characters: List<Character>) {
        Observable.fromCallable { characterDao.insertAll(characters) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                print("Inserted ${characters.size} users from API in DB...")
            }
    }





}