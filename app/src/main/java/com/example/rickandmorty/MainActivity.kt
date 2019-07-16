package com.example.rickandmorty

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rickandmorty.repository.CharacterRepository
import com.example.rickandmorty.service.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cr = CharacterRepository(application)
        //Log.d("ashish",cr.getCharacterFromApi().toString())

        disposable =
        cr.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.d("ashish",it.size.toString())

            },{
                Log.d("ashish","MyError $it")
            },{
                Log.d("ashish","Completed")
            },{
                Log.d("ashish","Subscribed")
            })


        /*disposable =

        RetrofitInstance.api.getCharacter("2")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.d("ashish",result.name) },
                { error -> Log.d("ashish","Error $error") }
            )*/

        /*disposable =

            RetrofitInstance.api.getAllCharacter(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> Log.d("ashish",result.info.count.toString()+" "+result.results[0].name) },
                    { error -> Log.d("ashish","Error $error") }
                )*/




    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
