package com.dldmswo1209.caerangtutor.model

import com.google.gson.annotations.SerializedName

data class Post(
    val funcName: String, // 함수 이름
    val language: String, // 언어
    val code: String, // 코드
    val description: String, // 함수 설명
    val writer: User, // 작성자
    var key: String = ""
){
    constructor(): this("","","","",User())
}
