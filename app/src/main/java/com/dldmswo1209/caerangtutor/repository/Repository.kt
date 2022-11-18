package com.dldmswo1209.caerangtutor.repository

import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance

class Repository {
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)

    suspend fun getFunction(keyword: String) = retrofit.getFunction(keyword)

}