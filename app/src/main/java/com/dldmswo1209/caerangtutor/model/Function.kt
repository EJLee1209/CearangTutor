package com.dldmswo1209.caerangtutor.model

//"id": 1,
//"func_name": "add",
//"description": "두 정수를 더하는 함수",
//"content": "const add = (a, b) => {\n    return a   b;\n}",
//"author": "길홍배",
//"language": "JavaScript",
//"date": "2022-10-29T15:00:00.000Z"

data class Function(
    val id: Int,
    val func_name : String,
    val description : String,
    val content : String,
    val author : String,
    val language : String,
    val date : String
)
