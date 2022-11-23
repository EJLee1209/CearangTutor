package com.dldmswo1209.caerangtutor.retrofitApi

import com.dldmswo1209.caerangtutor.model.Function
import com.dldmswo1209.caerangtutor.model.AddFunctionBody
import com.dldmswo1209.caerangtutor.model.SignInBody
import com.dldmswo1209.caerangtutor.model.SignUpBody
import retrofit2.Call
import retrofit2.http.*

interface MyApi {

    // 함수 이름으로 코드 가져오기
    @GET("/func/funcName/{keyword}")
    suspend fun getFunction(
        @Path("keyword") keyword: String
    ) : List<Function>

    @GET("/func/author/{keyword}")
    suspend fun getFunctionFromAuthor(
        @Path("keyword") keyword: String
    ): List<Function>

    // 회원가입
    @POST("/user/signup")
    fun signUp(
        @Body signUpBody: SignUpBody
    ) :Call<String>

    // 로그인
    @POST("/user/login")
    fun signIn(
        @Body signInBody: SignInBody
    ) : Call<SignUpBody>

    @POST("/func")
    fun addFunction(
        @Body functionAddBody: AddFunctionBody
    ): Call<String>

}

