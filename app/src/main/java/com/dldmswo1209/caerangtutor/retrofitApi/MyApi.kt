package com.dldmswo1209.caerangtutor.retrofitApi

import com.dldmswo1209.caerangtutor.model.Function
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("/func")
    suspend fun getFunction(
        @Query("searchType") searchType : String,
        @Query("keyWord") keyWord : String
    ) : List<Function>

}

