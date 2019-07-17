package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rickandmorty.repository.CharacterRepository
import com.example.rickandmorty.viewmodel.CharacterViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()
    lateinit var cvm : CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvm = CharacterViewModel(application,1)

        disposable.add(
            cvm.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    Log.d("ashish","getChar() fetched ${it.size} items")

                },{
                    Log.d("ashish","MyError $it")
                },{
                    Log.d("ashish","Completed")
                },{
                    Log.d("ashish","Subscribed getCharacter()")
                })
        )

    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
        cvm.clearCharacterDisposable()
    }
}
