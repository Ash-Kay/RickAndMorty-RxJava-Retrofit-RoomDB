package com.example.rickandmorty

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rickandmorty.repository.CharacterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cr = CharacterRepository(application)
        //Log.d("ashish",cr.getCharacterFromApi().toString())

        /*cr.getCharacterFromApi()
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
            })*/

        cr.getCharacterFromApi2()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("ashish",it.result.size.toString())
            }




    }
}
