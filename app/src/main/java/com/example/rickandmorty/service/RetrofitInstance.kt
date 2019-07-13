package com.example.rickandmorty.service

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.create

object RetrofitInstance {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://rickandmortyapi.com/api/"

    /**
     * Create an instance of Retrofit object
     */
    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val api = getInstance().create<Api>()

}