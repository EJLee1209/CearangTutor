package com.dldmswo1209.caerangtutor.retrofitApi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

// 레트로핏 객체 생성을 위한 object
object RetrofitInstance {
    val BASE_URL = "http://18.182.30.21:1111"

    val gson = GsonBuilder().setLenient().create()
    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): Retrofit{
        return client
    }
}