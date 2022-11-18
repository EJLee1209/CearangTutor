package com.dldmswo1209.caerangtutor.model

import com.google.gson.annotations.SerializedName

data class Function(
    @SerializedName("분류코드") val id: Int,
    @SerializedName("함수이름") val func_name : String,
    @SerializedName("함수설명") val description : String,
    @SerializedName("함수코드") val content : String,
    @SerializedName("작성자") val author : String,
    @SerializedName("언어") val language : String,
    @SerializedName("업데이트날짜") val date : String
)
