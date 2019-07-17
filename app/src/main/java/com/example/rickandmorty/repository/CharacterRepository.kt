package com.example.rickandmorty.repository

import android.app.Application
import android.util.Log
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterDatabase
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterRepository (application: Application, val pageNo : Int) {

    private val disposable = CompositeDisposable()

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
            .doOnError {
                Log.d("ashish","Error in concat")
            }
            .doOnNext {
                Log.d("ashish","Concat successful")
            }
    }

    fun getCharacterFromDb(): Observable<List<Character>>{

        return characterDao.getAll().filter { it.isNotEmpty() }
            .doOnError {
                Log.d("ashish","Error fetch DB.. $it")
            }
            .toObservable()
            .doOnNext {
                Log.d("ashish","Dispatching ${it.size} users from DB...")
            }

    }

    fun getCharacterFromApi(): Observable<List<Character>>{
        return RetrofitInstance.api.getAllCharacter(pageNo.toString())
            .flatMap {
                Observable.fromArray(it.results)
            }
            .doOnError {
                //getCharacterFromDb()// no need i guess
                Log.d("ashish","network error fetch from db")
            }
            .doOnNext {
                Log.d("ashish","${it.size} items fetched from api and stored in db")
                storeCharactersInDb(it)
            }

    }

    fun storeCharactersInDb(characters: List<Character>) {
        disposable.add(
            Observable.fromCallable { characterDao.insertAll(characters) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnComplete {
                    print("Inserted ${characters.size} users from API in DB...")
                }
                .subscribe {
                    print("Inserted ${characters.size} users from API in DB...")
                }
        )

    }

    fun clearDisposable(){
        disposable.clear()
    }

}